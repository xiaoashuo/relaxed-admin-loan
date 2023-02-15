package com.relaxed.boot.risk.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询对象
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.273
 */
@Data
@Accessors(chain = true)
@Schema(title = "")
public class RiskRuleQO implements Serializable {

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

}
