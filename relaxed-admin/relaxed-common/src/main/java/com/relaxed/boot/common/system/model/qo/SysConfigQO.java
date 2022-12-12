package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 基础配置 查询对象
 *
 * @author yakir 2022-11-15 10:24:40
 */
@Data
@Schema(title = "基础配置")
public class SysConfigQO {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Integer id;

	/**
	 * 配置名称
	 */
	@Schema(title = "配置名称", description = "配置名称")
	private String name;

	/**
	 * 配置键
	 */
	@Schema(title = "配置键", description = "配置键")
	private String confKey;

}
