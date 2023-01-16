package com.relaxed.boot.loan.model.vo;

import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.entity.OrderCustomer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
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




    @Schema(description = "审核信息")
    private AuditInfo auditInfo;



    @Schema(description = "银行卡列表")
    private List<OrderBankCard> orderBankCardList;

    @Data
    public static class AuditInfo{
        @Schema(title = "主键", description = "主键")
        private Long auditId;

        @Schema(title = "系统审核", description = "系统审核")
        private String auditStatus;

        /**
         * 风控审核出结果的时间
         */
        @Schema(title = "风控审核出结果的时间", description = "风控审核出结果的时间")
        private LocalDateTime resultTime;
        @Schema(description = "审核详情")
        private List<AuditDetail> auditDetails;
    }
    @Data
    public static class AuditDetail {
        /**
         * 拒绝原因码仅拒绝时返回，可多个以“,”分隔
         */
        @Schema(title = "拒绝原因码仅拒绝时返回，可多个以“,”分隔", description = "拒绝原因码仅拒绝时返回，可多个以“,”分隔")
        private String reasonCode;

        /**
         * 拒绝原因描述 仅拒绝时返回
         */
        @Schema(title = "拒绝原因描述 仅拒绝时返回", description = "拒绝原因描述 仅拒绝时返回")
        private String reasonDesc;
    }
}
