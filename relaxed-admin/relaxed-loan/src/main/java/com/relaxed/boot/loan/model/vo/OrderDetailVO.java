package com.relaxed.boot.loan.model.vo;

import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.entity.OrderGuarantor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Yakir
 * @Topic OrderDetailVO
 * @Description
 * @date 2023/1/10 17:17
 * @Version 1.0
 */
@Data
public class OrderDetailVO {
    @Schema(description = "订单信息")
    private Order order;
    @Schema(description = "联系人信息")
    private OrderCustomer orderCustomer;
    @Schema(description = "房屋相关信息")
    private OrderGuarantor orderGuarantor;
    @Schema(description = "银行卡列表")
    private List<OrderBankCard> orderBankCardList;
}
