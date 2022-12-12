package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 角色菜单表 查询对象
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@Schema(title = "角色菜单表")
public class SysRoleMenuQO {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Long id;

}
