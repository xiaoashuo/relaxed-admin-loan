package com.relaxed.boot.risk.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Yakir
 * @since 2021-08-29T12:14:38.328
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("risk_field")
@Schema(title = "")
public class RiskField extends Model<RiskField> {

	/**
	 * 主键
	 */
	@TableId(value = "id")
	@Schema(title = "主键", description = "主键")
	private Long id;

	/**
	 * MODEL ID
	 */
	@Schema(title = "模型ID", description = "模型ID")
	private Long modelId;

	/**
	 * 事件信息中的包含的字段
	 */
	@Schema(title = "事件信息中的包含的字段", description = "事件信息中的包含的字段")
	private String fieldName;

	/**
	 *
	 */
	@Schema(title = "字段标签", description = "字段标签")
	private String label;

	/**
	 * 字段类型
	 */
	@Schema(title = "字段类型", description = "字段类型")
	private String fieldType;

	/**
	 * 校验类型
	 */
	@Schema(title = "校验类型", description = "校验类型")
	private String validateType;

	/**
	 * 校验参数
	 */
	@Schema(title = "校验参数", description = "校验参数")
	private String validateArgs;

	/**
	 * 1 标识索引字段
	 */
	@Schema(title = "1 标识索引字段", description = "1 标识索引字段")
	private Integer indexed;

	/**
	 *
	 */
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 *
	 */
	@Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;

}
