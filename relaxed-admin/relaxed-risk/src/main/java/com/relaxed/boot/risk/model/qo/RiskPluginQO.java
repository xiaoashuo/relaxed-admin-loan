package com.relaxed.boot.risk.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询对象
 *
 * @author Yakir
 * @since 2021-09-28T13:43:11.834
 */
@Data
@Accessors(chain = true)
@Schema(title = "")
public class RiskPluginQO implements Serializable {

	/**
	 * 主键id
	 */
	@Schema(title = "主键id", description = "主键id")
	private Long id;

}
