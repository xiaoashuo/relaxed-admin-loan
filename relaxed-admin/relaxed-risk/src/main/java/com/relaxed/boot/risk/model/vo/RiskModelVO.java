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
 * @since 2021-08-29T09:04:20.388
 */
@Data
@Accessors(chain = true)
@Schema(title = "")
public class RiskModelVO implements Serializable {

	/**
	 * 主键
	 */
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
	@Schema(title = "模型名称", description = "模型名称")
	private String modelName;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String label;

	/**
	 * 事件中标识实体的主键
	 */
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
	@Schema(title = "", description = "")
	private LocalDateTime createdTime;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private LocalDateTime updatedTime;

}
