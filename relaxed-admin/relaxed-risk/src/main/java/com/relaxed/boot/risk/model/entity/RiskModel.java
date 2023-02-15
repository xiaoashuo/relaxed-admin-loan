package com.relaxed.boot.risk.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Yakir
 * @since 2021-08-29T09:04:20.388
 */
@Data
@Accessors(chain = true)
@TableName("risk_model")
@Schema(title = "")
public class RiskModel implements Serializable {

	/**
	 * 主键
	 */
	@TableId(value = "id")
	@Schema(title = "主键", description = "主键")
	private Long id;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String guid;

	/**
	 * 模型名称
	 */
	@NotEmpty(message = "模型名称不能为空")
	@Schema(title = "模型名称", description = "模型名称")
	private String modelName;

	/**
	 * 模型标签
	 */
	@NotEmpty(message = "模型标签不能为空")
	@Schema(title = "模型标签", description = "模型标签")
	private String label;

	/**
	 * 事件中标识实体的主键
	 */
	@NotEmpty(message = "模型实体名称不能为空")
	@Schema(title = "事件中标识实体的主键", description = "事件中标识实体的主键")
	private String entityName;

	/**
	 * 事件主键
	 */
	@Schema(title = "事件主键", description = "事件主键")
	private String entryName;

	/**
	 * 事件时间
	 */
	@Schema(title = "事件时间", description = "事件时间")
	private String referenceDate;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Integer fieldValidate;



	/**
	 * 状态
	 */
	@Schema(title = "状态", description = "状态")
	private Integer status;


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
