package com.relaxed.boot.loan.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.dto.OrderDTO;
import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.entity.OrderGuarantor;
import com.relaxed.boot.loan.model.vo.OrderDetailVO;
import com.relaxed.boot.loan.model.vo.OrderPageVO;
import com.relaxed.boot.loan.model.qo.OrderQO;
import com.relaxed.boot.loan.mapper.OrderMapper;
import com.relaxed.boot.loan.service.AuditService;
import com.relaxed.boot.loan.service.OrderBankCardService;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.boot.loan.service.OrderGuarantorService;
import com.relaxed.boot.loan.service.OrderService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
@RequiredArgsConstructor
@Service
public class OrderServiceImpl extends ExtendServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderCustomerService orderCustomerService;
    private final OrderGuarantorService orderGuarantorService;

    private final OrderBankCardService orderBankCardService;

    private final AuditService auditService;
    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<OrderPageVO> 分页数据
    */
    @Override
    public PageResult<OrderPageVO> queryPage(PageParam pageParam, OrderQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public Order getByPartnerBizNo(String partnerBizNo) {
        return getOne(Wrappers.lambdaQuery(Order.class )
                .eq(Order::getPartnerBizNo,partnerBizNo));
    }

    @Override
    public OrderDTO getOrderFormDetailDTO(Long orderId) {
        Order order = getById(orderId);
        Assert.notNull(order, "订单{}信息不存在",orderId);
        OrderCustomer orderCustomer = orderCustomerService.getByOrderId(orderId);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setPeriodUnit(order.getPeriodUnit());
        orderDTO.setInterestRateUnit(order.getInterestRateUnit());
        orderDTO.setRepaymentWay(order.getRepaymentWay());
        orderDTO.setApplyAmount(order.getApplyAmount());
        orderDTO.setPartnerBizNo(order.getPartnerBizNo());
        orderDTO.setLoanPeriod(order.getLoanPeriod());
        orderDTO.setInterestRate(order.getInterestRate());
        orderDTO.setProductCode(order.getProductCode());
        orderDTO.setTrustPlanCode(order.getTrustPlanCode());
        orderDTO.setRealName(orderCustomer.getRealName());
        orderDTO.setMobileNo(orderCustomer.getMobileNo());
        orderDTO.setCertificateNo(orderCustomer.getCertificateNo());
        orderDTO.setEmail(orderCustomer.getEMail());
        orderDTO.setCertificateStartDate(orderCustomer.getCertificateValidityDate());
        orderDTO.setCertificateEndDate(orderCustomer.getCertificateExpiryDate());
        orderDTO.setResidenceAddress(orderCustomer.getResidenceAddress());
        orderDTO.setMailingAddress(orderCustomer.getMailingAddress());
        orderDTO.setResidenceCode(orderCustomer.getResidenceCode());
        orderDTO.setMailingCode(orderCustomer.getMailingCode());
        orderDTO.setJobYears(orderCustomer.getJobYears());
        orderDTO.setAnnualIncome(orderCustomer.getAnnualIncome());
        orderDTO.setCompanyName(orderCustomer.getCompanyName());
        orderDTO.setGraceDays(order.getGraceDays());
        orderDTO.setPenaltyRate(order.getPenaltyInterestRate());
        orderDTO.setEmploymentStatus(orderCustomer.getEmploymentStatus());
        orderDTO.setProfessionCode(orderCustomer.getProfessionCode());
        orderDTO.setLoanPurpose(order.getLoanPurpose());
        orderDTO.setContractNo(order.getPartnerContractNo());
        orderDTO.setGuaranteeWay(order.getGuaranteeWay());
        orderDTO.setInvestType(order.getInvestType());
        orderDTO.setBusinessDetail(order.getBusinessDetail());
        orderDTO.setIndustryDetail(order.getIndustryDetail());
        orderDTO.setRepaymentSource(order.getRepaymentSource());
        orderDTO.setInvestIndustry(order.getInvestIndustry());
        orderDTO.setLoanType(order.getLoanType());
        orderDTO.setCertificateType(orderCustomer.getCertificateType());
        orderDTO.setCustomType(orderCustomer.getCustomType());
        orderDTO.setGender(orderCustomer.getGender());
        orderDTO.setAge(orderCustomer.getAge());
        orderDTO.setBirthDate(orderCustomer.getBirthDate());
        orderDTO.setLoanPeriod(order.getLoanPeriod());
        orderDTO.setInterestRate(order.getInterestRate());
        orderDTO.setAnnualIncome(orderCustomer.getAnnualIncome());
        orderDTO.setGraceDays(order.getGraceDays());
        return orderDTO;
    }

    @Override
    public OrderDetailVO getOrderDetail(Long orderId) {
        Order order = getById(orderId);
        Assert.notNull(order, "订单{}信息不存在",orderId);
        OrderCustomer orderCustomer= orderCustomerService.getByOrderId(orderId);
        OrderGuarantor orderGuarantor=orderGuarantorService.getByOrderId(orderId);
        List<OrderBankCard> orderBankCardList= orderBankCardService.listByOrderId(orderId);
        Audit audit=auditService.getByOrderId(orderId);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrder(order);
        orderDetailVO.setOrderCustomer(orderCustomer);
        orderDetailVO.setOrderGuarantor(orderGuarantor);
        orderDetailVO.setOrderBankCardList(orderBankCardList);
        OrderDetailVO.AuditInfo auditInfo = new OrderDetailVO.AuditInfo();
        auditInfo.setAuditId(audit.getAuditId());
        auditInfo.setAuditStatus(audit.getAuditStatus());
        auditInfo.setResultTime(audit.getResultTime());
        String reasonCode = audit.getReasonCode();
        String reasonDesc = audit.getReasonDesc();
        List<OrderDetailVO.AuditDetail> auditDetailList=new ArrayList<>();
        if (StrUtil.isNotEmpty(reasonCode)){
            String[] reasonCodeArr = reasonCode.split(",");
            String[] reasonDescArr = StrUtil.isEmpty(reasonDesc) ? new String[]{} : reasonDesc.split(",");
            int descLength = reasonDescArr.length;
            for (int i = 0; i <reasonCodeArr.length; i++) {
                String code = reasonCodeArr[i];
                OrderDetailVO.AuditDetail auditDetail = new OrderDetailVO.AuditDetail();
                auditDetail.setReasonCode(code);
                if (i+1>=descLength&&descLength>0){
                    auditDetail.setReasonDesc(reasonDescArr[i]);
                }
                auditDetailList.add(auditDetail);
            }
        }
        auditInfo.setAuditDetails(auditDetailList);
        orderDetailVO.setAuditInfo(auditInfo);
        return orderDetailVO;
    }
}
