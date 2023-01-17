package com.relaxed.boot.loan.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.loan.enums.AuditStatusEnum;
import com.relaxed.boot.loan.enums.OrderEnum;
import com.relaxed.boot.loan.manage.LoanManage;
import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.service.AuditService;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.boot.loan.service.OrderService;
import com.relaxed.boot.loan.util.LogFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yakir
 * @Topic RiskResultQuery
 * @Description
 * @date 2023/1/16 17:47
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class RiskResultQuery {

    private final OrderService orderService;

    private final LoanManage loanManage;



    private final AuditService auditService;
    @Scheduled(fixedRate = 60*1000)
    public void riskResultQuery(){
        log.info(LogFormatUtil.format("风控审核查询","开始",LocalDateTime.now()));
        List<Order> orderList= orderService.listByStatus(OrderEnum.OrderStatus.CREDITING.getVal());
        if (CollectionUtil.isEmpty(orderList)){
          log.info(LogFormatUtil.format("风控审核查询","效验",LocalDateTime.now(),"待查数据为空"));
          return;
        }
        for (Order order : orderList) {
            Long orderId = order.getOrderId();
            String partnerBizNo = order.getPartnerBizNo();
            //风控结果查询 默认通过订单金额大于1200 通过 小于1200 失败
            BigDecimal applyAmount = order.getApplyAmount();
            BigDecimal splitLine = BigDecimal.valueOf(1200);
            try {
                Audit audit = auditService.getByOrderId(orderId);
                if (splitLine.compareTo(applyAmount)<=0){
                    //成功
                    // 设置订单状态及通过字段
                    order.setOrderStage(OrderEnum.OrderStatus.CREDIT_SUCCESS.getVal());
                    order.setApplyPassedAmount(order.getApplyAmount());
                    order.setLoanPeriodPassed(order.getLoanPeriod());
                    order.setInterestRateUnitPassed(order.getInterestRateUnitPassed());
                    order.setInterestRatePassed(order.getInterestRate());

                    audit.setAuditStatus(AuditStatusEnum.EXAM_PASS.getCode());

                }else{
                    //小于等于1200 失败
                    order.setOrderStage(OrderEnum.OrderStatus.CREDIT_DENY.getVal());
                    audit.setAuditStatus(AuditStatusEnum.EXAM_DENY.getCode());
                    audit.setReasonCode("20002");
                    audit.setReasonDesc("模拟拒绝");
                }
                orderService.updateById(order);

                auditService.updateById(audit);
                loanManage.loanConfirm(orderId);
                //触发放款
            } catch (Exception e) {
                log.error(LogFormatUtil.format("风控审核查询","异常",LocalDateTime.now(),StrUtil.format("订单{}",partnerBizNo)),e);
            }

        }

        log.info(LogFormatUtil.format("风控审核查询","结束",LocalDateTime.now()));

    }


}
