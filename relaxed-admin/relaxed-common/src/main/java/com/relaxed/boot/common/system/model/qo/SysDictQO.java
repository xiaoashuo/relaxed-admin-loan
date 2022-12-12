package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 字典表 查询对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "字典表")
public class SysDictQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 标识
	 */
	@Schema(title = "标识", description = "标识")
	private String code;

	/**
	 * 名称
	 */
	@Schema(title = "名称", description = "名称")
	private String title;

}
