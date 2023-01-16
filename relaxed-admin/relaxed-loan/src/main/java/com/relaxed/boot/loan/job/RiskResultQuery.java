package com.relaxed.boot.loan.job;

import cn.hutool.core.collection.CollectionUtil;
import com.relaxed.boot.loan.enums.OrderEnum;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    public void riskResultQuery(){

      List<Order> orderList= orderService.listByStatus(OrderEnum.OrderStatus.CREDITING.getVal());
      if (CollectionUtil.isEmpty(orderList)){
          log.info("风控审核待查询数据为空");
          return;
      }
        for (Order order : orderList) {
            //风控结果查询 默认通过订单金额大于1200 通过 小于1200 失败
            BigDecimal applyAmount = order.getApplyAmount();
            BigDecimal splitLine = BigDecimal.valueOf(1200);
            if (splitLine.compareTo(applyAmount)>0){
                //成功
                // 设置订单状态及通过字段
                order.setOrderStage(OrderEnum.OrderStatus.CREDIT_SUCCESS.getVal());
                order.setApplyPassedAmount(order.getApplyAmount());
                order.setLoanPeriodPassed(order.getLoanPeriod());
                order.setInterestRateUnitPassed(order.getInterestRateUnitPassed());
                order.setInterestRatePassed(order.getInterestRate());
            }else{
                //小于等于1200 失败
                order.setOrderStage(OrderEnum.OrderStatus.CREDIT_DENY.getVal());
            }
            orderService.updateById(order);
            //触发放款

        }

    }
}
