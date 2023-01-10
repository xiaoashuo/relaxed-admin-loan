package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单银行卡放款详情表分页视图对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "订单银行卡放款详情表分页视图对象")  
public class OrderBankCardDetailPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 银行卡主键
     */
    @Schema(title = "银行卡主键", description = "银行卡主键")
    private Long detailId;
    
    /**
     * 订单号,关联t_order表
     */
    @Schema(title = "订单号,关联t_order表", description = "订单号,关联t_order表")
    private Long orderId;
    
    /**
     * 银行卡编号,关联t_order_bank_card表
     */
    @Schema(title = "银行卡编号,关联t_order_bank_card表", description = "银行卡编号,关联t_order_bank_card表")
    private Long bankCardId;
    
    /**
     * 共同借款人编号，关联t_order_borrow
     */
    @Schema(title = "共同借款人编号，关联t_order_borrow", description = "共同借款人编号，关联t_order_borrow")
    private Long userId;
    
    /**
     * 是否主借人0:非主借人 1:主借人
     */
    @Schema(title = "是否主借人0:非主借人 1:主借人", description = "是否主借人0:非主借人 1:主借人")
    private Integer loanType;
    
    /**
     * 姓名
     */
    @Schema(title = "姓名", description = "姓名")
    private String realName;
    
    /**
     * 证件号码
     */
    @Schema(title = "证件号码", description = "证件号码")
    private String certificateNo;
    
    /**
     * 放款金额
     */
    @Schema(title = "放款金额", description = "放款金额")
    private BigDecimal loanAmt;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Integer valid;
    
    /**
     * 创建时间
     */
    @Schema(title = "创建时间", description = "创建时间")
    private LocalDateTime createdTime;
    
    /**
     * 更新时间
     */
    @Schema(title = "更新时间", description = "更新时间")
    private LocalDateTime updatedTime;
    

}
