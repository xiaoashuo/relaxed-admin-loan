package com.relaxed.boot.loan.manage;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import com.relaxed.boot.loan.enums.BillEnum;
import com.relaxed.boot.loan.enums.BillItemSubjectEnum;
import com.relaxed.boot.loan.enums.FillerTargetEnum;
import com.relaxed.boot.loan.enums.FillerTypeEnum;
import com.relaxed.boot.loan.enums.LoanEnum;
import com.relaxed.boot.loan.enums.RepayEnum;
import com.relaxed.boot.loan.enums.TradeStatusEnum;
import com.relaxed.boot.loan.model.entity.Bill;
import com.relaxed.boot.loan.model.entity.BillItem;
import com.relaxed.boot.loan.model.entity.BillItemFiller;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.service.BillItemFillerService;
import com.relaxed.boot.loan.service.BillItemService;
import com.relaxed.boot.loan.service.BillService;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.loan.service.OrderBankCardService;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.boot.loan.service.OrderService;
import com.relaxed.boot.loan.service.TradeService;
import com.relaxed.boot.loan.util.ProNoUtil;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.result.SysResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yakir
 * @Topic LoanManage
 * @Description
 * @date 2023/1/17 9:43
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class LoanManage {
    private final LoanService loanService;

    private final BillService billService;

    private final BillItemService billItemService;

    private final BillItemFillerService billItemFillerService;

    private final TradeService tradeService;

    private final OrderService orderService;
    private final OrderBankCardService orderBankCardService;
    private final OrderCustomerService orderCustomerService;

    public void loanConfirm(Long orderId) {
        Order order = orderService.getById(orderId);
        Assert.notNull(order,"订单信息不能为空");
        Long receiveCardId = order.getReceiveCardId();
        //获取收款卡
        OrderBankCard orderBankCard = orderBankCardService.getById(receiveCardId);
        OrderCustomer orderCustomer = orderCustomerService.getByOrderId(orderId);
        Trade trade = convertToNewTrade(order,orderBankCard);
        tradeService.save(trade);
        Loan loan =convertToNewLoan(order,trade,orderCustomer);
        loanService.save(loan);
        //调用放款
    }
    private Loan convertToNewLoan(Order order, Trade trade, OrderCustomer orderCustomer) {
        Loan loan = new Loan();
        loan.setOrderId(order.getOrderId());
        loan.setPartnerBizNo(order.getPartnerBizNo());
        loan.setPartnerLoanNo(order.getPartnerBizNo());
        loan.setTradeId(trade.getTradeId());
        loan.setLoanAmt(trade.getTradeAmount());
        loan.setPrincipalReceivable(trade.getTradeAmount());
        loan.setTotalTerms(order.getLoanPeriod()); // 期数
        loan.setPeriodUnit(order.getPeriodUnit()); // 周期单位
        loan.setCurrentTerm(1);
        loan.setLoanStatus(LoanEnum.LoanStatus.GIVING_MONEY.getSnifCode());
        loan.setRealName(orderCustomer.getRealName());
        loan.setCertificateNo(orderCustomer.getCertificateNo());
        loan.setApplyDate(order.getCreatedTime());
        loan.setInterestRate(order.getInterestRate());
        loan.setInterestRateUnit(order.getInterestRateUnit());
        loan.setPenaltyRate(order.getPenaltyInterestRate());
        loan.setProductCode(String.valueOf(order.getProductCode()));
        loan.setProductName(order.getProductName());
        loan.setTrustPlanCode(String.valueOf(order.getTrustPlanCode()));
        loan.setTrustPlanName(order.getTrustPlanName());
        // 合同编号新加字段用信时已传过来落order
        loan.setContractNo(order.getPartnerContractNo());
        // 还款方式取order的
        loan.setRepaymentWay(order.getRepaymentWay());
        // 贴息金额
        return loan;
    }
    private static Trade convertToNewTrade(Order order, OrderBankCard orderBankCard) {
        String tradeNo = ProNoUtil.generateTradeNo();
        Trade trade = new Trade();
        trade.setOrderId(order.getOrderId());
        trade.setPartnerBizNo(order.getPartnerBizNo());
        trade.setTradeNo(tradeNo);
        trade.setRepayType(order.getRepaymentWay());
        trade.setTradeStatus(TradeStatusEnum.CREATE.getCode());
        trade.setTradeAmount(order.getApplyAmount());
        trade.setTradeAccountBank(orderBankCard.getBankName());
        trade.setTradeAccount(orderBankCard.getAccountNo());
        trade.setTradeAccountType(orderBankCard.getAccountType().toString());
        trade.setTradeAccountName(orderBankCard.getAccountName());
        trade.setPayChannel("99");
        trade.setPayPlatformId("99");
        trade.setPayPlatformMerchantId("99");
        trade.setPayPlatformUserName("99");
        trade.setPayPlatformBusinessCode("99");
        trade.setTradeTime(LocalDateTime.now());
        trade.setLoanPurpose(LoanEnum.LoanPurpose.LOAN.getVal());
        return trade;
    }
    public void repayLoan(Long loanId) {
        Loan loan = loanService.getById(loanId);
        String partnerBizNo = loan.getPartnerBizNo();
        Assert.notNull(loan,()-> new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"合作方业务号(partner_biz_no) {} 借据不存在",partnerBizNo));
        Integer loanStatus = loan.getLoanStatus();
        Assert.isTrue(LoanEnum.LoanStatus.REPAYMENTING.getSnifCode().equals(loanStatus),()-> new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"合作方业务号(partner_biz_no) {} 借据状态不正确", partnerBizNo));
        Bill bill= billService.getByLoanId(loanId);
        Assert.notNull(bill,()-> new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"合作方业务号(partner_biz_no) {} 找不到有效账单",partnerBizNo) );
        Long billId = bill.getBillId();
        BillItem billItemPrin=billItemService.getByBillIdAndItemType(billId, BillItemSubjectEnum.PRINCIPAL.getCode());
        BillItem billItemInterest=billItemService.getByBillIdAndItemType(billId, BillItemSubjectEnum.INTEREST.getCode());
        BillItem billItemPenaltyInterest=billItemService.getByBillIdAndItemType(billId, BillItemSubjectEnum.INTEREST_PENALTY.getCode());
        Assert.isTrue(null != billItemPrin && null != billItemInterest && null != billItemPenaltyInterest,
                ()-> new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"合作方业务号(partner_biz_no) {} 本利罚科目表有缺失",partnerBizNo)    );
        //创建还款明细
        Trade trade = convertToRepaymentTrade(loan);
        tradeService.save(trade);
        //填充loan
        fillLoan(loan,LoanEnum.LoanStatus.CLEAR.getSnifCode());
        loanService.updateById(loan);
        //填充账单
        fillBill(bill,LoanEnum.LoanStatus.CLEAR.getSnifCode());
        billService.updateById(bill);
        //本金科目 和filler填充
        fillBillItem( billItemPrin);
        billItemService.updateById(billItemPrin);
        BillItemFiller bifPrin = convertBillItemFiller(loan, billItemPrin.getReceiptsAmt(), bill, billItemPrin, LocalDateTime.now(), trade,
                FillerTypeEnum.A);
        billItemFillerService.save(bifPrin);
        //利息科目 和filler填充
        BigDecimal realReceivableAmt = billItemInterest.getReceivableAmt();
        LocalDateTime nowData = LocalDateTime.now();
        fillBillItem(billItemInterest, nowData, realReceivableAmt, realReceivableAmt);
        billItemService.updateById(billItemInterest);
        BillItemFiller bifInterest = convertBillItemFiller(loan, billItemInterest.getReceiptsAmt(), bill, billItemInterest, nowData,
                trade, FillerTypeEnum.A);
        billItemFillerService.save(bifInterest);
        //罚息科目 和filler填充
        BigDecimal realRepayPintAmt = billItemPenaltyInterest.getReceivableAmt();
        fillBillItem(billItemPenaltyInterest, nowData, realRepayPintAmt, realRepayPintAmt);
        billItemService.updateById(billItemPenaltyInterest);
        BillItemFiller bifPint = convertBillItemFiller(loan, billItemPenaltyInterest.getReceiptsAmt(), bill, billItemPenaltyInterest,
                nowData, trade, FillerTypeEnum.A);
        billItemFillerService.save(bifPint);


    }
    private BillItemFiller convertBillItemFiller(Loan loan, BigDecimal repayIntAmt, Bill bill,
                                                 BillItem billItemInterest, LocalDateTime nowDate, Trade trade, FillerTypeEnum a) {
        Long tradeId = trade.getTradeId();
        BillItemFiller bifInterest = new BillItemFiller();
        bifInterest.setBillId(bill.getBillId());
        bifInterest.setBillItemId(billItemInterest.getBillItemId());
        bifInterest.setLoanId(loan.getLoanId());
        bifInterest.setFillerType(a.getCode());
        bifInterest.setFillerAmount(repayIntAmt);
        bifInterest.setFillerTarget(FillerTargetEnum.RECEIPTS.getVal());
//        bifInterest.setRepayType(trade.getRepayType());
        bifInterest.setTradeId(tradeId);
        //bifInterest.setRealFillerTime(trade.getTradeTime());
        bifInterest.setPartnerFillerTime(nowDate);
        //bifInterest.setLatestSyncTime(nowDate);
        return bifInterest;
    }
    private void fillBillItem(BillItem billItemInterest, LocalDateTime nowDate, BigDecimal receivableAmt,
                              BigDecimal receiptsAmt) {
        billItemInterest.setReceivableAmt(receivableAmt);
        billItemInterest.setReceiptsAmt(receiptsAmt);
        billItemInterest.setLatestSyncTime(nowDate);
        billItemInterest.setUpdatedTime(nowDate);
    }
    private void fillBillItem( BillItem billItemInterest) {
        // 实收本金
        BigDecimal repayPrinAmt = billItemInterest.getReceivableAmt();

        // billItemInterest.setReceivableAmt(repayPrinAmt);
        billItemInterest.setReceiptsAmt(repayPrinAmt);
        LocalDateTime nowDate = LocalDateTime.now();
        billItemInterest.setLatestSyncTime(nowDate);
        billItemInterest.setUpdatedTime(nowDate);
    }
    private void fillBill(Bill bill, Integer clearFlag) {
        LocalDateTime nowDate = LocalDateTime.now();
//        // 实收总金额，单位：元
//        BigDecimal repayAmt = content.getRepay_amt();
//        //实收利息
//        BigDecimal repayIntAmt = content.getRepay_int_amt();
//        //实收罚息
//        BigDecimal repayPintAmt = content.getRepay_pint_amt();
//        // 减免利息
//        BigDecimal exemptIntAmt = content.getExempt_int_amt();
//        // 减免罚息
//        BigDecimal exemptPintAmt = content.getExempt_pint_amt();
        if (LoanEnum.LoanStatus.CLEAR.getSnifCode().equals(clearFlag)) {
            LocalDateTime tradeTime = LocalDateTime.now();
            bill.setClearDate(
                    tradeTime);
            bill.setTermStatus(BillEnum.BillTermStatus.CLEAR.getVal());
        }
        // 应收

        bill.setBillReceivableAmt(
                bill.getBillReceivableAmt());
        // 实收含减免
        bill.setBillReceiptsAmt(
                bill.getBillReceivableAmt());
        // 减免
        bill.setBillMitigateAmt(bill.getBillMitigateAmt());
        bill.setLatestSyncTime(nowDate);
        bill.setUpdatedTime(nowDate);
    }
    private void fillLoan( Loan loan, Integer clearFlag) {
//        // 实收总金额，单位：元
//        BigDecimal repayAmt = content.getRepay_amt();
//        // 实收本金
//        BigDecimal repayPrinAmt = content.getRepay_prin_amt();
//        // 实收利息
//        BigDecimal repayIntAmt = content.getRepay_int_amt();
//        // 实收罚息
//        BigDecimal repayPintAmt = content.getRepay_pint_amt();
//        // 减免利息
//        BigDecimal exemptIntAmt = content.getExempt_int_amt();
//        // 减免罚息
//        BigDecimal exemptPintAmt = content.getExempt_pint_amt();
//        // 减免利息+减免罚息
//        loan.setReducedAmt(exemptIntAmt.add(exemptPintAmt));
        // 已还本金
        loan.setPrincipalReceipts(loan.getPrincipalReceivable());
        // 已还利息
        BigDecimal realInterestReceipts = loan.getInterestReceivable();
        loan.setInterestReceivable(realInterestReceipts);
        loan.setInterestReceipts(realInterestReceipts);
        // 已还罚息=实收罚息+减免罚息

        BigDecimal realPenaltyReceipts = loan.getPenaltyReceivable();
        loan.setPenaltyReceivable(realPenaltyReceipts);
        loan.setPenaltyReceipts(realPenaltyReceipts);
        // 应还总金额 已还总金额

        BigDecimal realReceiptsAmt = loan.getLoanAmt();
        loan.setReceiptsAmt(realReceiptsAmt);
        loan.setReceivableAmt(realReceiptsAmt);
        if (LoanEnum.LoanStatus.CLEAR.getSnifCode().equals(clearFlag)) {
            LocalDateTime tradeTime = LocalDateTime.now();
            loan.setLoanStatus(LoanEnum.LoanStatus.CLEAR.getSnifCode());
            loan.setClearDate(
                    tradeTime);
        }
        // loan.setClearEvent(requestDto.getContent().getClear_event());
        loan.setLatestSyncTime(LocalDateTime.now());
        loan.setUpdatedTime(LocalDateTime.now());
    }
    private Trade convertToRepaymentTrade(Loan loan) {
        LocalDateTime tradeTime = LocalDateTime.now();
        BigDecimal repayAmt = loan.getLoanAmt();
        Trade trade = new Trade();
        trade.setOrderId(loan.getOrderId());
        trade.setPartnerBizNo(loan.getPartnerBizNo());
        trade.setTradeNo(ProNoUtil.generateTradeNo());
        trade.setTradeTime(tradeTime);
        trade.setRepayType(RepayEnum.RepayWay.CUSTOMER_REPAYMENT.getVal());
        trade.setTradeStatus(TradeStatusEnum.SUCCESS_PAY.getCode());
        trade.setTradeAmount(repayAmt);
        trade.setLoanPurpose(LoanEnum.LoanPurpose.REPAYMENT.getVal());
        trade.setFailReason("");
        return trade;
    }
}
