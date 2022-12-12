package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典项 查询对象
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@Accessors(chain = true)
@Schema(title = "字典项")
public class SysDictItemQO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Schema(title = "ID", description = "ID")
	private Integer id;

	/**
	 * 字典Code
	 */
	@Schema(title = "字典Code", description = "字典Code")
	private String dictCode;

}
