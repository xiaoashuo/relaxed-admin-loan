package com.relaxed.boot.risk.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 查询对象
 *
 * @author Yakir
 * @since 2021-08-29T12:14:38.328
 */
@Data
@Accessors(chain = true)
@Schema(title = "")
public class RiskFieldQO implements Serializable {

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Long id;

}
