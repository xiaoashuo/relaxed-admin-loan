package com.relaxed.boot.loan.manage;

import com.relaxed.boot.loan.enums.BillItemSubjectEnum;
import com.relaxed.boot.loan.enums.FileTypeEnum;
import com.relaxed.boot.loan.enums.LoanEnum;
import com.relaxed.boot.loan.enums.TradeStatusEnum;
import com.relaxed.boot.loan.model.entity.*;
import com.relaxed.boot.loan.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Yakir
 * @Topic PayManage
 * @Description
 * @date 2023/1/16 17:32
 * @Version 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PayManage {

    private final StampManage stampManage;
    private final TradeService tradeService;

    private final LoanService loanService;

    private final BillService billService;

    private final BillItemService billItemService;


    public  void  loadSuccessHandle(Trade trade, Loan loan){
        //MOCK 成功
        LocalDateTime mockFintime = LocalDateTime.now();
        Trade updateTrade = new Trade();
        updateTrade.setTradeId(trade.getTradeId());
        updateTrade.setTradeStatus(TradeStatusEnum.SUCCESS_PAY.getCode());
        updateTrade.setTradeTime(mockFintime);
        tradeService.updateById(updateTrade);
        LocalDateTime tradeTime = trade.getTradeTime();

        loan.setLoanDate(tradeTime);
        loan.setStartDate(tradeTime);
        loan.setReceivableAmt(loan.getLoanAmt());
        loan.setEndDate(tradeTime.plusDays(loan.getTotalTerms()));
        loan.setLoanStatus(LoanEnum.LoanStatus.REPAYMENTING.getSnifCode());
        loan.setLatestSyncTime(LocalDateTime.now());
        loan.setUpdatedTime(LocalDateTime.now());
        loanService.updateById(loan);
        // 创建单期的账单 以及本利罚三个科目
        Bill bill = new Bill();
        bill.setLoanId(loan.getLoanId());
        bill.setTermNo(1);
        bill.setStartDate(trade.getTradeTime());

        bill.setEndDate(trade.getTradeTime().plusDays(loan.getTotalTerms()));
        bill.setDueDate(bill.getEndDate());
        bill.setTermStatus(1);
        // 本金加利息，利息 = 本金*天数*(年利率/360)
        bill.setBillReceivableAmt(loan.getLoanAmt());
        // bill.setBillReceivableAmt(CalcRecvUtil.calcBillReceivableAmt(loan.getInterestRate(),
        // loan.getLoanAmt(), 1));
        bill.setLatestSyncTime(LocalDateTime.now());
        billService.save(bill);

        // 本利罚
        createBillItem(loan, bill, BillItemSubjectEnum.PRINCIPAL.getCode(), loan.getLoanAmt());
        createBillItem(loan, bill, BillItemSubjectEnum.INTEREST.getCode(), new BigDecimal("0"));
        createBillItem(loan, bill, BillItemSubjectEnum.INTEREST_PENALTY.getCode(), new BigDecimal("0"));
        //生成借款合同
        stampManage.generateRelatedFile(loan.getPartnerBizNo(),FileTypeEnum.A9);



    }



    public void loanFailHandle( Trade trade, Loan loan) {
        String errMsg="mock异常错误";
        LocalDateTime mockFintime = LocalDateTime.now();
        Integer loanStatus=4;
        Trade updateTrade = new Trade();
        updateTrade.setTradeId(trade.getTradeId());
        updateTrade.setTradeStatus(TradeStatusEnum.FAILED_PAY.getCode());
        updateTrade.setFailReason(errMsg);

        updateTrade.setTradeTime(mockFintime);
        updateTrade.setFailReason(loanStatus + errMsg);
        tradeService.updateById(updateTrade);
        Loan updateLoan = new Loan();
        updateLoan.setLoanId(loan.getLoanId());
        updateLoan.setLoanDate(updateTrade.getTradeTime());
        updateLoan.setLoanStatus(LoanEnum.LoanStatus.GIVE_MONEY_FAIL.getSnifCode());
        loanService.updateById(updateLoan);
    }

    public void loanUnKnownHandle( Trade trade, Loan loan) {
        String errMsg="mock未知错误";
        Trade updateTrade = new Trade();
        updateTrade.setTradeId(trade.getTradeId());
        updateTrade.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        updateTrade.setFailReason(errMsg);
        tradeService.updateById(updateTrade);
    }
    public void createBillItem(Loan loan, Bill bill, Integer itemType, BigDecimal receivableAmt) {
        BillItem billItem = new BillItem();
        billItem.setLoanId(loan.getLoanId());
        billItem.setBillId(bill.getBillId());
        billItem.setItemType(itemType);
        billItem.setReceivableAmt(receivableAmt);
        billItem.setLatestSyncTime(LocalDateTime.now());
        billItemService.save(billItem);

        if (itemType.equals(BillItemSubjectEnum.INTEREST.getCode())) {
            // 如果是利息，则更新借据表的应还利息
            loan.setInterestReceivable(receivableAmt);
            loan.setUpdatedTime(LocalDateTime.now());
            loan.setLatestSyncTime(LocalDateTime.now());
            loanService.updateById(loan);
        }
    }
}
