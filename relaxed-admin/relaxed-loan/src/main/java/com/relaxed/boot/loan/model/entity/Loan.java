package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 借款合同表
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@TableName("t_loan")
@Schema(title = "借款合同表")  
public class Loan {

	private static final long serialVersionUID = 1L;

	/**
	 * 借款编号
	 */
	@TableId
    @Schema(title = "借款编号", description = "借款编号")
	private Long loanId;
    
	/**
	 * 订单编号,关联t_order表
	 */
    @Schema(title = "订单编号,关联t_order表", description = "订单编号,关联t_order表")
	private Long orderId;
    
	/**
	 * 借款编号(云融提供)
	 */
    @Schema(title = "借款编号(云融提供)", description = "借款编号(云融提供)")
	private String loanBizNo;
    
	/**
	 * 合作方业务号
	 */
    @Schema(title = "合作方业务号", description = "合作方业务号")
	private String partnerBizNo;
    
	/**
	 * 合作方借款编号
	 */
    @Schema(title = "合作方借款编号", description = "合作方借款编号")
	private String partnerLoanNo;
    
	/**
	 * 合同号
	 */
    @Schema(title = "合同号", description = "合同号")
	private String contractNo;
    
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
	 * 信托计划代号
	 */
    @Schema(title = "信托计划代号", description = "信托计划代号")
	private String trustPlanCode;
    
	/**
	 * 信托计划名称
	 */
    @Schema(title = "信托计划名称", description = "信托计划名称")
	private String trustPlanName;
    
	/**
	 * 客户姓名
	 */
    @Schema(title = "客户姓名", description = "客户姓名")
	private String realName;
    
	/**
	 * 证件类型，枚举值：1-户口簿2-护照5-港澳居民来往内地通行证6-台湾同胞来往内地通行证8-外国人居留证9-警官证A-香港身份证B-澳门身份证C-台湾身份证X-其他证件10-居民身份证及其他以公民身份证号码为标识的证件20-军人身份证件
	 */
    @Schema(title = "证件类型，枚举值：1-户口簿2-护照5-港澳居民来往内地通行证6-台湾同胞来往内地通行证8-外国人居留证9-警官证A-香港身份证B-澳门身份证C-台湾身份证X-其他证件10-居民身份证及其他以公民身份证号码为标识的证件20-军人身份证件", description = "证件类型，枚举值：1-户口簿2-护照5-港澳居民来往内地通行证6-台湾同胞来往内地通行证8-外国人居留证9-警官证A-香港身份证B-澳门身份证C-台湾身份证X-其他证件10-居民身份证及其他以公民身份证号码为标识的证件20-军人身份证件")
	private String certificateType;
    
	/**
	 * 证件号码
	 */
    @Schema(title = "证件号码", description = "证件号码")
	private String certificateNo;
    
	/**
	 * 贷款申请日期
	 */
    @Schema(title = "贷款申请日期", description = "贷款申请日期")
	private LocalDateTime applyDate;
    
	/**
	 * 放款时间
	 */
    @Schema(title = "放款时间", description = "放款时间")
	private LocalDateTime loanDate;
    
	/**
	 * 贷款确认日期（起息日）
	 */
    @Schema(title = "贷款确认日期（起息日）", description = "贷款确认日期（起息日）")
	private LocalDateTime startDate;
    
	/**
	 * 贷款到期日期
	 */
    @Schema(title = "贷款到期日期", description = "贷款到期日期")
	private LocalDateTime endDate;
    
	/**
	 * 结清日期
	 */
    @Schema(title = "结清日期", description = "结清日期")
	private LocalDateTime clearDate;
    
	/**
	 * 还款日
	 */
    @Schema(title = "还款日", description = "还款日")
	private Integer repayDay;
    
	/**
	 * 放款交易编号
	 */
    @Schema(title = "放款交易编号", description = "放款交易编号")
	private Long tradeId;
    
	/**
	 * 放款金额
	 */
    @Schema(title = "放款金额", description = "放款金额")
	private BigDecimal loanAmt;
    
	/**
	 * 币种（0:RMB）
	 */
    @Schema(title = "币种（0:RMB）", description = "币种（0:RMB）")
	private String currency;
    
	/**
	 * 还款方式（0:等额本息 1:等额本金 2:先息后本 3:月息季本 4:X+Y,X期先息后本，Y期等额本金 5:一次性还本付息）
	 */
    @Schema(title = "还款方式（0:等额本息 1:等额本金 2:先息后本 3:月息季本 4:X+Y,X期先息后本，Y期等额本金 5:一次性还本付息）", description = "还款方式（0:等额本息 1:等额本金 2:先息后本 3:月息季本 4:X+Y,X期先息后本，Y期等额本金 5:一次性还本付息）")
	private Integer repaymentWay;
    
	/**
	 * 期数单位，0:天，1:月
	 */
    @Schema(title = "期数单位，0:天，1:月", description = "期数单位，0:天，1:月")
	private String periodUnit;
    
	/**
	 * 总期数
	 */
    @Schema(title = "总期数", description = "总期数")
	private Integer totalTerms;
    
	/**
	 * irr年利率
	 */
    @Schema(title = "irr年利率", description = "irr年利率")
	private BigDecimal irrRate;
    
	/**
	 * 当前期数
	 */
    @Schema(title = "当前期数", description = "当前期数")
	private Integer currentTerm;
    
	/**
	 * 商户帖息金额
	 */
    @Schema(title = "商户帖息金额", description = "商户帖息金额")
	private BigDecimal merchantDisAmt;
    
	/**
	 * 宽限期天数
	 */
    @Schema(title = "宽限期天数", description = "宽限期天数")
	private Integer graceDays;
    
	/**
	 * 利率单位（1:日利率 30:月利率 365:年利率）
	 */
    @Schema(title = "利率单位（1:日利率 30:月利率 365:年利率）", description = "利率单位（1:日利率 30:月利率 365:年利率）")
	private Integer interestRateUnit;
    
	/**
	 * 利率
	 */
    @Schema(title = "利率", description = "利率")
	private BigDecimal interestRate;
    
	/**
	 * 利率单位（1:日利率 30:月利率 365:年利率）
	 */
    @Schema(title = "利率单位（1:日利率 30:月利率 365:年利率）", description = "利率单位（1:日利率 30:月利率 365:年利率）")
	private String penaltyRateUnit;
    
	/**
	 * 罚息利率
	 */
    @Schema(title = "罚息利率", description = "罚息利率")
	private BigDecimal penaltyRate;
    
	/**
	 * 借款状态(20:待放款21:放款成功/还款中22:放款失败30:已结清40:回购中 99:转出)
	 */
    @Schema(title = "借款状态(20:待放款21:放款成功/还款中22:放款失败30:已结清40:回购中 99:转出)", description = "借款状态(20:待放款21:放款成功/还款中22:放款失败30:已结清40:回购中 99:转出)")
	private Integer loanStatus;
    
	/**
	 * 逾期标记（0:未逾期 1:已逾期）
	 */
    @Schema(title = "逾期标记（0:未逾期 1:已逾期）", description = "逾期标记（0:未逾期 1:已逾期）")
	private Integer overdueFlag;
    
	/**
	 * 应还本金
	 */
    @Schema(title = "应还本金", description = "应还本金")
	private BigDecimal principalReceivable;
    
	/**
	 * 已还本金
	 */
    @Schema(title = "已还本金", description = "已还本金")
	private BigDecimal principalReceipts;
    
	/**
	 * 应还利息
	 */
    @Schema(title = "应还利息", description = "应还利息")
	private BigDecimal interestReceivable;
    
	/**
	 * 已还利息
	 */
    @Schema(title = "已还利息", description = "已还利息")
	private BigDecimal interestReceipts;
    
	/**
	 * 应还罚息
	 */
    @Schema(title = "应还罚息", description = "应还罚息")
	private BigDecimal penaltyReceivable;
    
	/**
	 * 已还罚息
	 */
    @Schema(title = "已还罚息", description = "已还罚息")
	private BigDecimal penaltyReceipts;
    
	/**
	 * 应还总金
	 */
    @Schema(title = "应还总金", description = "应还总金")
	private BigDecimal receivableAmt;
    
	/**
	 * 已还总金
	 */
    @Schema(title = "已还总金", description = "已还总金")
	private BigDecimal receiptsAmt;
    
	/**
	 * 最后同步时间
	 */
    @Schema(title = "最后同步时间", description = "最后同步时间")
	private LocalDateTime latestSyncTime;
    
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;
    
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;
    
	/**
	 * 宁波银行放款入参借款合同编号
	 */
    @Schema(title = "宁波银行放款入参借款合同编号", description = "宁波银行放款入参借款合同编号")
	private String nbContractId;
    

}
