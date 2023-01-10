package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 分页视图对象
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@Schema(title = "分页视图对象")  
public class TradePageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 交易编号
     */
    @Schema(title = "交易编号", description = "交易编号")
    private Long tradeId;
    
    /**
     * 订单主键 t_order
     */
    @Schema(title = "订单主键 t_order", description = "订单主键 t_order")
    private Long orderId;
    
    /**
     * 合作方编号
     */
    @Schema(title = "合作方编号", description = "合作方编号")
    private String partnerBizNo;
    
    /**
     * 交易流水号
     */
    @Schema(title = "交易流水号", description = "交易流水号")
    private String tradeNo;
    
    /**
     * 还款方式(各项目不同)：
1客户还款
2-机构垫付（单期代偿）
3-机构回购（整笔借据全部回购）
     */
    @Schema(title = "还款方式", description = "还款方式")
    private Integer repayType;
    
    /**
     * 交易状态：
0- 交易创建 
1- 等待付款 
2-支付成功 
4-支付失败
     */
    @Schema(title = "交易状态", description = "交易状态")
    private Integer tradeStatus;
    
    /**
     * 交易金额
     */
    @Schema(title = "交易金额", description = "交易金额")
    private BigDecimal tradeAmount;
    
    /**
     * 交易银行名称
     */
    @Schema(title = "交易银行名称", description = "交易银行名称")
    private String tradeAccountBank;
    
    /**
     * 交易账号
     */
    @Schema(title = "交易账号", description = "交易账号")
    private String tradeAccount;
    
    /**
     * 账户类型
     */
    @Schema(title = "账户类型", description = "账户类型")
    private String tradeAccountType;
    
    /**
     * 账户姓名 
     */
    @Schema(title = "账户姓名 ", description = "账户姓名 ")
    private String tradeAccountName;
    
    /**
     * 帐户身份证号 
     */
    @Schema(title = "帐户身份证号 ", description = "帐户身份证号 ")
    private String tradeAccountId;
    
    /**
     * 支付渠道，例如：通联
     */
    @Schema(title = "支付渠道，例如：通联", description = "支付渠道，例如：通联")
    private String payChannel;
    
    /**
     * 放款通道 （核心库t-config-platform配置id）
     */
    @Schema(title = "放款通道 （核心库t-config-platform配置id）", description = "放款通道 （核心库t-config-platform配置id）")
    private String payPlatformId;
    
    /**
     * 商户id
     */
    @Schema(title = "商户id", description = "商户id")
    private String payPlatformMerchantId;
    
    /**
     * 用户名
     */
    @Schema(title = "用户名", description = "用户名")
    private String payPlatformUserName;
    
    /**
     * 业务code
     */
    @Schema(title = "业务code", description = "业务code")
    private String payPlatformBusinessCode;
    
    /**
     * 交易时间
     */
    @Schema(title = "交易时间", description = "交易时间")
    private LocalDateTime tradeTime;
    
    /**
     * 交易用途(1:放款  2:收款)
     */
    @Schema(title = "交易用途(1:放款  2:收款)", description = "交易用途(1:放款  2:收款)")
    private Integer loanPurpose;
    
    /**
     * 交易备注
     */
    @Schema(title = "交易备注", description = "交易备注")
    private String failReason;
    
    /**
     * 
     */
    @Schema(title = "", description = "")
    private String tradeRemark;
    
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
