package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 分页视图对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "分页视图对象")  
public class OrderBankCardPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 银行卡主键
     */
    @Schema(title = "银行卡主键", description = "银行卡主键")
    private Long bankCardId;
    
    /**
     * 订单号,关联t_order表
     */
    @Schema(title = "订单号,关联t_order表", description = "订单号,关联t_order表")
    private Long orderId;
    
    /**
     * 账户类型（0-对公 1-对私 2-第三方支付）
     */
    @Schema(title = "账户类型（0-对公 1-对私 2-第三方支付）", description = "账户类型（0-对公 1-对私 2-第三方支付）")
    private Integer accountType;
    
    /**
     * 户名
     */
    @Schema(title = "户名", description = "户名")
    private String accountName;
    
    /**
     * 户号
     */
    @Schema(title = "户号", description = "户号")
    private String accountNo;
    
    /**
     * 银行代号
     */
    @Schema(title = "银行代号", description = "银行代号")
    private String bankCode;
    
    /**
     * 银行名称
     */
    @Schema(title = "银行名称", description = "银行名称")
    private String bankName;
    
    /**
     * 开户行
     */
    @Schema(title = "开户行", description = "开户行")
    private String openBankName;
    
    /**
     * 开户行（分支行）联行号
     */
    @Schema(title = "开户行（分支行）联行号", description = "开户行（分支行）联行号")
    private String openBankUnionBank;
    
    /**
     * 预留手机号
     */
    @Schema(title = "预留手机号", description = "预留手机号")
    private String reserveMobile;
    
    /**
     * 银行卡用途1:收款 2:还款 3:认证
     */
    @Schema(title = "银行卡用途1:收款 2:还款 3:认证", description = "银行卡用途1:收款 2:还款 3:认证")
    private Integer cardPurpose;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Integer valid;
    
    /**
     * 认证状态0:未认证1:已认证
     */
    @Schema(title = "认证状态0:未认证1:已认证", description = "认证状态0:未认证1:已认证")
    private Integer authStatus;
    
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