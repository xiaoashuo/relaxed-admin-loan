package com.relaxed.boot.loan.manage;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.loan.enums.AuditStatusEnum;
import com.relaxed.boot.loan.enums.LoanEnum;
import com.relaxed.boot.loan.enums.OrderEnum;
import com.relaxed.boot.loan.enums.TradeStatusEnum;
import com.relaxed.boot.loan.model.dto.OrderDTO;
import com.relaxed.boot.loan.model.dto.SaveOrderResult;
import com.relaxed.boot.loan.model.entity.*;
import com.relaxed.boot.loan.service.*;
import com.relaxed.boot.loan.util.ProNoUtil;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.result.SysResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ProjectService projectService;

    private final PartnerService partnerService;
    private final LoanService loanService;
    public SaveOrderResult saveOrder(OrderDTO orderDTO) {
        Integer projectId = orderDTO.getProjectId();
        Project project = projectService.getById(projectId);
        Integer productId = project.getProductId();
        Integer trustPlanId = project.getTrustPlanId();
        Integer partnerId = project.getPartnerId();
        Partner partner = partnerService.getById(partnerId);
        Product product=productService.getById(productId);
        TrustPlan trustPlan=trustPlanService.getById(trustPlanId);

        String partnerBizNo = IdUtil.getSnowflakeNextIdStr();
        orderDTO.setPartnerBizNo(partnerBizNo);
        Order order = convertToNewOrder(orderDTO,project,partner ,product, trustPlan);
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

    private static Order convertToNewOrder(OrderDTO orderDTO, Project project,Partner partner,Product product, TrustPlan trustPlan) {
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
        order.setGraceDays(order.getGraceDays());
        order.setPartnerId(partner.getPartnerId());
        order.setPartnerName(partner.getPartnerAlias());
        order.setTrustPlanCode(Integer.valueOf(trustPlan.getTrustPlanNo()));
        order.setTrustPlanName(trustPlan.getTrustPlanName());
        order.setProductCode(Integer.valueOf(product.getProductCode()));
        order.setProductName(product.getProductName());
        order.setProjectId(project.getProjectId());
        return order;
    }

    public SaveOrderResult submitOrder(Long orderId) {
        Order order = orderService.getById(orderId);
        Assert.notNull(order,"订单信息不能为空");
        String partnerBizNo = order.getPartnerBizNo();
        //提取收款卡
        List<OrderBankCard> orderBankCardList = orderBankCardService.listByOrderId(orderId);
        List<OrderBankCard> loanBankCards = orderBankCardList.stream().filter(item -> LoanEnum.LoanPurpose.LOAN.getVal().equals(item.getCardPurpose())).collect(Collectors.toList());
        Assert.notEmpty(loanBankCards,()->new BusinessException(SysResultCode.SERVER_ERROR.getCode(),"订单号{},放款卡不能为空",partnerBizNo));
        OrderBankCard orderBankCard = loanBankCards.get(0);
        Long receiveBankCardId = orderBankCard.getBankCardId();
        order.setReceiveCardId(receiveBankCardId);
        orderService.updateById(order);
        //开始调用风控
        //获取风控结果
        //执行放款信息
        OrderCustomer orderCustomer = orderCustomerService.getByOrderId(orderId);
        Audit audit = new Audit();
        audit.setCreditId(0L);
        audit.setOrderId(orderId);
        audit.setProductCode(order.getProductCode()+"");
        audit.setProductName(order.getProductName());
        audit.setStrategyCode(order.getProductCode()+"02");
        audit.setName(orderCustomer.getRealName());
        audit.setIdNo(orderCustomer.getCertificateNo());
        audit.setMobileNo(orderCustomer.getMobileNo());
        audit.setAuditStatus(AuditStatusEnum.WAITING_EXAM.getCode());
        audit.setResTime(LocalDateTime.now());
        audit.setResultTime(LocalDateTime.now());
        auditService.save(audit);
        order.setOrderStage(OrderEnum.OrderStatus.CREDITING.getVal());
        orderService.updateById(order);

        SaveOrderResult saveOrderResult = new SaveOrderResult();
        saveOrderResult.setOrderId(orderId);
        saveOrderResult.setPartnerBizNo(partnerBizNo);
        return saveOrderResult;
    }






}
