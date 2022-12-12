package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 系统角色 查询对象
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@Schema(title = "系统角色")
public class SysRoleQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	@Schema(title = "角色名称", description = "角色名称")
	private String name;

	/**
	 * 角色标识
	 */
	@Schema(title = "角色标识", description = "角色标识")
	private String code;

}
