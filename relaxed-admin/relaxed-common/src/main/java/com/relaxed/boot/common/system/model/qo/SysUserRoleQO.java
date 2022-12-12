package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 用户角色表 查询对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "用户角色表")
public class SysUserRoleQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色Code
	 */
	@Schema(title = "角色Code", description = "角色Code")
	private String roleCode;

}
