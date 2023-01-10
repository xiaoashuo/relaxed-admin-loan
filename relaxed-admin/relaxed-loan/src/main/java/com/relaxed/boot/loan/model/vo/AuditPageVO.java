package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 审核表分页视图对象
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@Schema(title = "审核表分页视图对象")  
public class AuditPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键", description = "主键")
    private Long auditId;
    
    /**
     * 授信编号，关联t_credit表
     */
    @Schema(title = "授信编号，关联t_credit表", description = "授信编号，关联t_credit表")
    private Long creditId;
    
    /**
     * 订单编号，关联t_order表
     */
    @Schema(title = "订单编号，关联t_order表", description = "订单编号，关联t_order表")
    private Long orderId;
    
    /**
     * 产品代号
     */
    @Schema(title = "产品代号", description = "产品代号")
    private String productCode;
    
    /**
     * 产品名称
     */
    @Schema(title = "产品名称", description = "产品名称")
    private String productName;
    
    /**
     * 风控策略码
     */
    @Schema(title = "风控策略码", description = "风控策略码")
    private String strategyCode;
    
    /**
     * 客户姓名
     */
    @Schema(title = "客户姓名", description = "客户姓名")
    private String name;
    
    /**
     * 身份证号码
     */
    @Schema(title = "身份证号码", description = "身份证号码")
    private String idNo;
    
    /**
     * 银行卡号
     */
    @Schema(title = "银行卡号", description = "银行卡号")
    private String bankCardNo;
    
    /**
     * 手机号
     */
    @Schema(title = "手机号", description = "手机号")
    private String mobileNo;
    
    /**
     * 其余的额外参数
     */
    @Schema(title = "其余的额外参数", description = "其余的额外参数")
    private String extParamJsonStr;
    
    /**
     * 系统审核：审批标记
Y:通过
N:拒绝
P:审核中
E:系统内部异常
     */
    @Schema(title = "系统审核", description = "系统审核")
    private String auditStatus;
    
    /**
     * 人工审核：审批标记
Y:通过
N:拒绝
P:审核中
E:系统内部异常
     */
    @Schema(title = "人工审核", description = "人工审核")
    private String auditStatusPeople;
    
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
    
    /**
     * 响应时间
     */
    @Schema(title = "响应时间", description = "响应时间")
    private LocalDateTime resTime;
    
    /**
     * 风控审核出结果的时间
     */
    @Schema(title = "风控审核出结果的时间", description = "风控审核出结果的时间")
    private LocalDateTime resultTime;
    
    /**
     * 人工审核出结果时间
     */
    @Schema(title = "人工审核出结果时间", description = "人工审核出结果时间")
    private LocalDateTime peopleResultTime;
    
    /**
     * 黑名单标记 B类、C类、逗号分隔
     */
    @Schema(title = "黑名单标记 B类、C类、逗号分隔", description = "黑名单标记 B类、C类、逗号分隔")
    private String approvalMark;
    
    /**
     * 百行征信报告id(如果风控规则有的话)
     */
    @Schema(title = "百行征信报告id(如果风控规则有的话)", description = "百行征信报告id(如果风控规则有的话)")
    private String bhCreditRptId;
    
    /**
     * 百融特殊名单报告id(如果风控规则有的话)
     */
    @Schema(title = "百融特殊名单报告id(如果风控规则有的话)", description = "百融特殊名单报告id(如果风控规则有的话)")
    private String brSpeclistRptId;
    
    /**
     * 百融借贷意向报告id(如果风控规则有的话)
     */
    @Schema(title = "百融借贷意向报告id(如果风控规则有的话)", description = "百融借贷意向报告id(如果风控规则有的话)")
    private String brIntentionRptId;
    
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
