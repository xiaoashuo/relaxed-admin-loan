package com.relaxed.boot.risk.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 视图层 计算规则 运算符 和比列 基础分 运算符 （额外分*比列） 小于等于 最大分
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.273
 */
@Data
@Accessors(chain = true)
@Schema(title = "风控规则视图")
public class RiskRuleVO implements Serializable {

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Long id;

	/**
	 * 模型ID
	 */
	@Schema(title = "模型ID", description = "模型ID")
	private Long modelId;

	/**
	 * 策略ID
	 */
	@Schema(title = "策略ID", description = "策略ID")
	private Long activationId;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String name;

	/**
	 * 规则名称
	 */
	@Schema(title = "规则名称", description = "规则名称")
	private String label;

	/**
	 * 检验脚本
	 */
	@Schema(title = "检验脚本", description = "检验脚本")
	private String scripts;

	@Schema(title = "规则脚本入口", description = "规则脚本入口")
	private String ruleScriptEntry;

	/**
	 * 初始分数
	 */
	@Schema(title = "初始分数", description = "初始分数")
	private Integer initScore;

	/**
	 * 基数
	 */
	@Schema(title = "基数", description = "基数")
	private Integer baseNum;

	/**
	 * 运算符
	 */
	@Schema(title = "运算符", description = "运算符")
	private String operator;

	/**
	 * 比例
	 */
	@Schema(title = "比例", description = "比例")
	private Integer rate;

	/**
	 * 最大得分值
	 */
	@Schema(title = "最大得分值", description = "最大得分值")
	private Integer max;

	/**
	 * 状态
	 */
	@Schema(title = "状态", description = "状态")
	private Integer status;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String ruleDefinition;

	/**
	 *
	 */
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 更新时间
	 */
	@Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;

}
