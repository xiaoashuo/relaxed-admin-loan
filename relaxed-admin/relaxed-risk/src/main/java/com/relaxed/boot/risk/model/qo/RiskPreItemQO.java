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
 * @since 2021-08-29T13:57:50.664
 */
@Data
@Accessors(chain = true)
@Schema(title = "")
public class RiskPreItemQO implements Serializable {

	/**
	 * ID
	 */
	@Schema(title = "ID", description = "ID")
	private Long id;
	@NotNull(message = "模型id不能为空")
	private Long modelId;
	@Schema(title = "来源字段", description = "来源字段")
	private String sourceField;
	@Schema(title = "目标字段", description = "目标字段")
	private String destField;
}
