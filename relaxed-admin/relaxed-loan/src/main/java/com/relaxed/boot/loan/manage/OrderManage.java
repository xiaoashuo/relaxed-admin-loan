package com.relaxed.boot.loan.manage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.loan.enums.LoanEnum;
import com.relaxed.boot.loan.enums.TradeStatusEnum;
import com.relaxed.boot.loan.model.dto.OrderDTO;
import com.relaxed.boot.loan.model.dto.SaveOrderResult;
import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.entity.Product;
import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.model.entity.TrustPlan;
import com.relaxed.boot.loan.service.AuditService;
import com.relaxed.boot.loan.service.LoanService;
import com.relaxed.boot.loan.service.OrderBankCardService;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.boot.loan.service.OrderService;
import com.relaxed.boot.loan.service.ProductService;
import com.relaxed.boot.loan.service.TradeService;
import com.relaxed.boot.loan.service.TrustPlanService;
import com.relaxed.boot.loan.util.ProNoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Yakir
 * @Topic OrderManage
 * @Description
 * @date 2023/1/13 12:01
 * @Version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class OrderManage {

    private final TrustPlanService trustPlanService;
    private final ProductService productService;
    private final OrderCustomerService orderCustomerService;
    private final OrderService orderService;

    private final AuditService auditService;
    private final OrderBankCardService orderBankCardService;

    private final TradeService tradeService;

    private final LoanService loanService;
    public SaveOrderResult saveOrder(OrderDTO orderDTO) {
        Integer productCode = orderDTO.getProductCode();
        Integer trustPlanCode = orderDTO.getTrustPlanCode();
        Product product=productService.getByProductCode(productCode);
        TrustPlan trustPlan=trustPlanService.getByTrustPlanCode(trustPlanCode);
        String partnerBizNo = IdUtil.getSnowflakeNextIdStr();
        orderDTO.setPartnerBizNo(partnerBizNo);
        Order order = convertToNewOrder(orderDTO, product, trustPlan);
        OrderCustomer orderCustomer = convertToNewOrderCustomer(orderDTO);
        Long orderId = order.getOrderId();
        if (orderId == null){
            boolean saveOrderSuccess = orderService.save(order);
            orderId = order.getOrderId();
            orderCustomer.setOrderId(orderId);
            boolean saveOrderCustomerSuccess = orderCustomerService.save(orderCustomer);

        }else{
            //存在订单
            orderService.updateById(order);
            OrderCustomer sqlOrderCustomer = orderCustomerService.getByOrderId(orderId);
            orderCustomer.setOrderId(orderId);
            orderCustomer.setCustomerId(sqlOrderCustomer.getCustomerId());
            BeanUtil.copyProperties(orderCustomer, sqlOrderCustomer);

            orderCustomerService.updateById(sqlOrderCustomer);
        }




        SaveOrderResult saveOrderResult = new SaveOrderResult();
        saveOrderResult.setOrderId(order.getOrderId());
        saveOrderResult.setPartnerBizNo(order.getPartnerBizNo());

        return saveOrderResult;
    }

    private  OrderCustomer convertToNewOrderCustomer(OrderDTO orderDTO) {
        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setCreditId(0L);
        orderCustomer.setRealName(orderDTO.getRealName());
        orderCustomer.setCertificateType(orderDTO.getCertificateType());
        orderCustomer.setCertificateNo(orderDTO.getCertificateNo());
        orderCustomer.setCustomType(orderDTO.getCustomType());
        orderCustomer.setMobileNo(orderDTO.getMobileNo());
        orderCustomer.setEducationCode(99);
        orderCustomer.setDegreeCode(9);
        orderCustomer.setGender(orderDTO.getGender());
        orderCustomer.setAge(orderDTO.getAge());
        orderCustomer.setBirthDate(orderDTO.getBirthDate());
        orderCustomer.setResidenceAddress(orderDTO.getResidenceAddress());
        orderCustomer.setResidenceCode(orderDTO.getResidenceCode());
        orderCustomer.setEMail(orderDTO.getEmail());
        orderCustomer.setEmploymentStatus(orderDTO.getEmploymentStatus());
        orderCustomer.setProfessionCode(orderDTO.getProfessionCode());
        orderCustomer.setCompanyName(orderDTO.getCompanyName());
        orderCustomer.setCompanyTrade("99");
        orderCustomer.setMailingAddress(orderDTO.getMailingAddress());
        orderCustomer.setMailingCode(orderDTO.getMailingCode());
        orderCustomer.setJobYears(orderDTO.getJobYears());
        orderCustomer.setAnnualIncome(orderDTO.getAnnualIncome());
        orderCustomer.setCertificateValidityDate(orderDTO.getCertificateStartDate());
        orderCustomer.setCertificateExpiryDate(orderDTO.getCertificateEndDate());
        return orderCustomer;
    }

    private static Order convertToNewOrder(OrderDTO orderDTO, Product product, TrustPlan trustPlan) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setCreditId(0L);
        order.setPartnerBizNo(orderDTO.getPartnerBizNo());
        order.setApplyAmount(orderDTO.getApplyAmount());
        order.setRepaymentWay(orderDTO.getRepaymentWay());
        order.setPeriodUnit(orderDTO.getPeriodUnit());
        order.setLoanPeriod(orderDTO.getLoanPeriod());
        order.setInterestRateUnit(orderDTO.getInterestRateUnit());
        order.setInterestRate(orderDTO.getInterestRate());
        order.setPenaltyInterestRate(orderDTO.getPenaltyRate());
        order.setLoanType(orderDTO.getLoanType());
        order.setInvestIndustry(orderDTO.getInvestIndustry());
        order.setLoanPurpose(orderDTO.getLoanPurpose());
        order.setRepaymentSource(orderDTO.getRepaymentSource());
        order.setIndustryDetail(orderDTO.getIndustryDetail());
        order.setInvestType(orderDTO.getInvestType());
        order.setBusinessDetail(orderDTO.getBusinessDetail());
        order.setGuaranteeWay(orderDTO.getGuaranteeWay());
        order.setPartnerContractNo(orderDTO.getContractNo());
        order.setReceiveCardId(0L);
        order.setRepaymentCardId(0L);
        order.setPartnerId(1);
        order.setPartnerName("1");
        order.setTrustPlanCode(Integer.valueOf(trustPlan.getTrustPlanNo()));
        order.setTrustPlanName(trustPlan.getTrustPlanName());
        order.setProductCode(Integer.valueOf(product.getProductCode()));
        order.setProductName(product.getProductName());
        order.setProjectId(1);
        order.setGraceDays(order.getGraceDays());
        return order;
    }

    public SaveOrderResult submitOrder(Long orderId) {
        Order order = orderService.getById(orderId);
        Assert.notNull(order,"订单信息不能为空");
        //开始调用风控
        //获取风控结果
        //执行放款信息
        Audit audit = auditService.getByOrderId(orderId);



        return null;
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
        loan.setContractNo(order.getPartnerBizNo());
        // 还款方式取order的
        loan.setRepaymentWay(order.getRepaymentWay());
        // 贴息金额
        return null;
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
}
