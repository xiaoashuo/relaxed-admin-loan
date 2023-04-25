package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.relaxed.extend.mybatis.plus.alias.TableAlias;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@TableName("t_order")
@TableAlias("o")
@Schema(title = "")
public class Order {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	@Schema(title = "主键id", description = "主键id")
	private Long orderId;

	/**
	 * 授信编号，关联t_credit表
	 */
	@Schema(title = "授信编号，关联t_credit表", description = "授信编号，关联t_credit表")
	private Long creditId;

	/**
	 * 合作方业务号
	 */
	@Schema(title = "合作方业务号", description = "合作方业务号")
	private String partnerBizNo;

	/**
	 * 批次号
	 */
	@Schema(title = "批次号", description = "批次号")
	private String applyBatchNo;

	/**
	 * 地区代码
	 */
	@Schema(title = "地区代码", description = "地区代码")
	private String regionCode;

	/**
	 * 地区名称
	 */
	@Schema(title = "地区名称", description = "地区名称")
	private String regionName;

	/**
	 * 申请金额（单位:元）
	 */
	@Schema(title = "申请金额（单位:元）", description = "申请金额（单位:元）")
	private BigDecimal applyAmount;

	/**
	 * 申请通过金额（单位：元）
	 */
	@Schema(title = "申请通过金额（单位：元）", description = "申请通过金额（单位：元）")
	private BigDecimal applyPassedAmount;

	/**
	 * 还款方式（ 0:等额本息 1:等额本金 2:先息后本 3:月息季本 4:X+Y,X期先息后本，Y期等额本金 5:一次性还本付息 ）
	 */
	@Schema(title = "还款方式")
	private Integer repaymentWay;

	/**
	 * 订单阶段（ 0:待处理 1:已进件 2:待签约 5:系统拒绝 6:待进件 10:待审批 11:已审批 12:审批拒绝 20:待放款 21:已放款\还款中
	 * 22:放款失败 30:已结清 40:回购中 99:债权置换 ）
	 */
	@Schema(title = "订单阶段）", description = "订单阶段")
	private Integer orderStage;

	/**
	 * 拒绝理由
	 */
	@Schema(title = "拒绝理由", description = "拒绝理由")
	private String refuseReason;

	/**
	 * 期数单位： http://49.234.128.78/showdoc/web/#/27?page_id=1176
	 */
	@Schema(title = "期数单位")
	private String periodUnit;

	/**
	 * 借款期限
	 */
	@Schema(title = "借款期限", description = "借款期限")
	private Integer loanPeriod;

	/**
	 * 申请通过借款期限
	 */
	@Schema(title = "申请通过借款期限", description = "申请通过借款期限")
	private Integer loanPeriodPassed;

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
	 * 审核通过利率单位（1:日利率 30:月利率 365:年利率）
	 */
	@Schema(title = "审核通过利率单位（1:日利率 30:月利率 365:年利率）", description = "审核通过利率单位（1:日利率 30:月利率 365:年利率）")
	private String interestRateUnitPassed;

	/**
	 * 罚息利率
	 */
	@Schema(title = "罚息利率", description = "罚息利率")
	private BigDecimal penaltyInterestRate;

	/**
	 * 审核通过利率
	 */
	@Schema(title = "审核通过利率", description = "审核通过利率")
	private BigDecimal interestRatePassed;

	/**
	 * 贷款类型（1:流动资金贷款 2:固定资产贷款 3:境外筹资转贷 4:买方信贷 5:出口卖方信贷 6:项目融资 7:其他贷款）
	 */
	@Schema(title = "贷款类型（1:流动资金贷款 2:固定资产贷款 3:境外筹资转贷 4:买方信贷 5:出口卖方信贷 6:项目融资 7:其他贷款）",
			description = "贷款类型（1:流动资金贷款 2:固定资产贷款 3:境外筹资转贷 4:买方信贷 5:出口卖方信贷 6:项目融资 7:其他贷款）")
	private Integer loanType;

	/**
	 * 征信贷款种类（ 10- 短期贷款 11- 短期流动资金贷款 19- 短期其他贷款 20- 中期贷款 21- 中期流动资金贷款 22- 中期基建贷款 23-
	 * 中期技改贷款 29- 中期其他贷款 30- 长期贷款 31- 长期基建贷款 32- 长期技改贷款 39- 长期其他贷款）
	 */
	@Schema(title = "征信贷款种类", description = "征信贷款种类")
	private Integer creditLoanType;

	/**
	 * 投向行业（ 112201-基础产业 112202-房地产 112204-金融机构 112205-工商企业 112209-其他）
	 */
	@Schema(title = "投向行业")
	private Integer investIndustry;

	/**
	 * 贷款用途，枚举值：
	 *
	 * 1-无场景贷款 2-教育 3-医美 4-租房 5-数码 6-买车 7-装修 8-旅游 9-农业生产 10-企业经营 11-综合用款 12-商城购物 13-日常消费
	 * 99-未知
	 */
	@Schema(title = "贷款用途", description = "贷款用途")
	private Integer loanPurpose;

	/**
	 * 还款来源（116101 经营性现金流 116102 房地产项目销售收入 116103 信托资产转让或出售变现 116104 土地出让收入 116105 专项费用返还
	 * 116106 专项税收返还 116107 BOT或类似收入 116108 财政兜底 116109 其他）
	 */
	@Schema(title = "还款来源")
	private Integer repaymentSource;

	/**
	 * 行业明细（V0100:个人贷款）
	 */
	@Schema(title = "行业明细（V0100:个人贷款）", description = "行业明细（V0100:个人贷款）")
	private String industryDetail;

	/**
	 * 投入分类（V0100:个人贷款）
	 */
	@Schema(title = "投入分类（V0100:个人贷款）", description = "投入分类（V0100:个人贷款）")
	private String investType;

	/**
	 * 业务种类明细（ 11:个人住房贷款 12:个人商用房(包括商住两用)贷款 13:个人住房公积金贷款 21:个人汽车消费贷款 31:个人助学贷款 41:个人经营性贷款
	 * 51:农户贷款 71 准贷记卡 81 贷记卡 91 个人消费贷款 99 其他）
	 */
	@Schema(title = "业务种类明细")
	private Integer businessDetail;

	/**
	 * 担保方式（A:质押贷款 B:抵押贷款 B01 房地产抵押贷款 B99 其他抵押贷款 C:保证贷款 C01 联保贷款 C99:其他保证贷款 D:信用/免担保贷款
	 * E:组合担保 Z:其他）
	 */
	@Schema(title = "担保方式")
	private String guaranteeWay;

	/**
	 * 合作方合同号
	 */
	@Schema(title = "合作方合同号", description = "合作方合同号")
	private String partnerContractNo;

	/**
	 * 收款卡编号
	 */
	@Schema(title = "收款卡编号", description = "收款卡编号")
	private Long receiveCardId;

	/**
	 * 还款卡信息
	 */
	@Schema(title = "还款卡信息", description = "还款卡信息")
	private Long repaymentCardId;

	/**
	 * 信托项目编号t_config_partner表
	 */
	@Schema(title = "信托项目编号t_config_partner表", description = "信托项目编号t_config_partner表")
	private Integer partnerId;

	/**
	 * 信托项目名称
	 */
	@Schema(title = "信托项目名称", description = "信托项目名称")
	private String partnerName;

	/**
	 * 信托计划编号t_config_trust_plan表
	 */
	@Schema(title = "信托计划编号t_config_trust_plan表", description = "信托计划编号t_config_trust_plan表")
	private Integer trustPlanCode;

	/**
	 * 信托计划名称
	 */
	@Schema(title = "信托计划名称", description = "信托计划名称")
	private String trustPlanName;

	/**
	 * 信托产品编号t_config_product表
	 */
	@Schema(title = "信托产品编号t_config_product表", description = "信托产品编号t_config_product表")
	private Integer productCode;

	/**
	 * 信托产品名称
	 */
	@Schema(title = "信托产品名称", description = "信托产品名称")
	private String productName;

	/**
	 * 子产品编号,关联t_config_project表
	 */
	@Schema(title = "子产品编号,关联t_config_project表", description = "子产品编号,关联t_config_project表")
	private Integer projectId;

	/**
	 * 合作方编号
	 */
	@Schema(title = "合作方编号", description = "合作方编号")
	private String partnerNo;

	/**
	 * 逻辑删除标识，未删除为 0，已删除为删除时间
	 */
	@Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
	private Integer valid;

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
	 * 宽限期天数
	 */
	@Schema(title = "宽限期天数", description = "宽限期天数")
	private Integer graceDays;

}
