package com.relaxed.boot.risk.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询对象
 *
 * @author Yakir
 * @since 2021-08-29T12:14:38.328
 */
@Data
@Accessors(chain = true)
@Schema(title = "风控字段")
public class RiskFieldQO implements Serializable {

	/**
	 * 主键
	 */
	@NotNull(message = "模型ID不存在")
	@Schema(title = "模型ID不存在", description = "模型ID不存在")
	private Long modelId;

}
