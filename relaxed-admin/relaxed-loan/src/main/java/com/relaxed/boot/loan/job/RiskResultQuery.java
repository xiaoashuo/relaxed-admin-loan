package com.relaxed.boot.loan.job;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.relaxed.boot.loan.enums.AuditStatusEnum;
import com.relaxed.boot.loan.enums.OrderEnum;
import com.relaxed.boot.loan.manage.LoanManage;
import com.relaxed.boot.loan.model.bo.RiskReqBO;
import com.relaxed.boot.loan.model.bo.RiskResBO;
import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.service.AuditService;
import com.relaxed.boot.loan.service.OrderCustomerService;
import com.relaxed.boot.loan.service.OrderService;
import com.relaxed.boot.common.system.utils.LogFormatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    private final OrderCustomerService orderCustomerService;

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
            OrderCustomer orderCustomer = orderCustomerService.getByOrderId(orderId);
            //风控结果查询 默认通过订单金额大于1200 通过 小于1200 失败
            BigDecimal applyAmount = order.getApplyAmount();
            BigDecimal splitLine = BigDecimal.valueOf(1200);
            try {

                Audit audit = auditService.getByOrderId(orderId);

                String body=  new RiskReqBO().setGuid("D8A0B53B276E4071AF73145212DDE940")
                        .setReqId(IdUtil.getSnowflakeNextIdStr())
                        .put("partnerBizNo",partnerBizNo)
                        .put("applyAmount",applyAmount)
                        .put("createdTime", System.currentTimeMillis())
                        .put("realName",orderCustomer.getRealName())
                        .put("certificateNo",orderCustomer.getCertificateNo())
                        .toJson();
                String response = HttpUtil.post("http://localhost:9401/risk/services/v1/upload", body);
                RiskResBO riskResBO = JSONUtil.toBean(response, RiskResBO.class);
                Integer code = riskResBO.getCode();
                JSONObject data = riskResBO.getData();

                if (code==200){
                    RiskResBO.Risk risk = data.getByPath("report.activations.login_rule", RiskResBO.Risk.class);
                    String riskResult = risk.getRisk();
                    if ("PASS".equals(riskResult)){
                        //通过
                        // 设置订单状态及通过字段
                        order.setOrderStage(OrderEnum.OrderStatus.CREDIT_SUCCESS.getVal());
                        order.setApplyPassedAmount(order.getApplyAmount());
                        order.setLoanPeriodPassed(order.getLoanPeriod());
                        order.setInterestRateUnitPassed(order.getInterestRateUnitPassed());
                        order.setInterestRatePassed(order.getInterestRate());

                        audit.setAuditStatus(AuditStatusEnum.EXAM_PASS.getCode());
                    }
//                    else if ("REVIEW".equals(riskResult)){}
                    else{
                        //拒绝
                        order.setOrderStage(OrderEnum.OrderStatus.CREDIT_DENY.getVal());
                        audit.setAuditStatus(AuditStatusEnum.EXAM_DENY.getCode());
                        audit.setReasonCode("20003");
                        String ruleList = data.getByPath("report.activations.hitRuleList.login_rule", String.class);
                        List<RiskResBO.RiskRule> riskRuleList = JSONUtil.toBean(ruleList, new TypeReference<List<RiskResBO.RiskRule>>() {
                            @Override
                            public Type getType() {
                                return super.getType();
                            }
                        },false) ;

                        StringBuilder sb=new StringBuilder();
                        StringBuilder reasonCodeBuilder=new StringBuilder();
                        for (RiskResBO.RiskRule entry : riskRuleList) {
                            String key = entry.getKey();
                            BigDecimal entryValue = entry.getValue();
                            String desc = entry.getDesc();
                            String localReason=key+"-"+entryValue+"-"+desc;
                            sb.append(localReason).append(",");
                            reasonCodeBuilder.append("40001").append(",");
                        }
                        String reasonDesc=sb.length()>0?sb.deleteCharAt(sb.length()-1).toString():sb.toString();
                        String reasonCode=reasonCodeBuilder.length()>0?reasonCodeBuilder.deleteCharAt(reasonCodeBuilder.length()-1).toString():reasonCodeBuilder.toString();
                        audit.setReasonCode(reasonCode);
                        audit.setReasonDesc(reasonDesc);

                    }
                   // Map<String, Object> riskRuleMap = data.getByPath("report.activations.hitRuleMap.login_rule", Map.class);
                }else if (code==20003){
                    //错误map
                    order.setOrderStage(OrderEnum.OrderStatus.CREDIT_DENY.getVal());
                    audit.setAuditStatus(AuditStatusEnum.EXAM_DENY.getCode());
                    audit.setReasonCode("20003");
                    StringBuilder sb=new StringBuilder();
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        String localReason=key+"-"+value;
                        sb.append(localReason).append(",");
                    }
                    String reasonDesc=sb.length()>0?sb.deleteCharAt(sb.length()-1).toString():sb.toString();
                    audit.setReasonDesc(reasonDesc);
                }else{
                    //异常message
                    order.setOrderStage(OrderEnum.OrderStatus.CREDIT_DENY.getVal());
                    audit.setAuditStatus(AuditStatusEnum.EXAM_INTERNAL_ERR.getCode());
                    audit.setReasonCode("50002");
                    audit.setReasonDesc("系统内部异常");
                }
//
//                if (splitLine.compareTo(applyAmount)<=0){
//                    //成功
//                    // 设置订单状态及通过字段
//                    order.setOrderStage(OrderEnum.OrderStatus.CREDIT_SUCCESS.getVal());
//                    order.setApplyPassedAmount(order.getApplyAmount());
//                    order.setLoanPeriodPassed(order.getLoanPeriod());
//                    order.setInterestRateUnitPassed(order.getInterestRateUnitPassed());
//                    order.setInterestRatePassed(order.getInterestRate());
//
//                    audit.setAuditStatus(AuditStatusEnum.EXAM_PASS.getCode());
//
//                }else{
//                    //小于等于1200 失败
//                    order.setOrderStage(OrderEnum.OrderStatus.CREDIT_DENY.getVal());
//                    audit.setAuditStatus(AuditStatusEnum.EXAM_DENY.getCode());
//                    audit.setReasonCode("20002");
//                    audit.setReasonDesc("模拟拒绝");
//                }
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
