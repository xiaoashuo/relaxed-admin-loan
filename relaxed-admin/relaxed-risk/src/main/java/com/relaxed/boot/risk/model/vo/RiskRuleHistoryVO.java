package com.relaxed.boot.risk.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 视图层
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.317
 */
@Data
@Accessors(chain = true)
@Schema(title = "")
public class RiskRuleHistoryVO implements Serializable {

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Long id;

	/**
	 * 模型ID
	 */
	@Schema(title = "模型ID", description = "模型ID")
	private Long ruleId;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String merchantCode;

	/**
	 * 规则名称
	 */
	@Schema(title = "规则名称", description = "规则名称")
	private String label;

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
	 * 抽象名称
	 */
	@Schema(title = "抽象名称", description = "抽象名称")
	private String abstractionName;

	/**
	 * 比例
	 */
	@Schema(title = "比例", description = "比例")
	private Integer rate;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String ruleDefinition;

	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 更新时间
	 */
	@Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;

}
