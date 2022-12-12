package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101 查询对象
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@Schema(title = "菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101")
public class SysMenuQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@Schema(title = "菜单ID", description = "菜单ID")
	private Integer id;

	/**
	 * 父级ID
	 */
	@Schema(title = "父级ID", description = "父级ID")
	private Integer parentId;

	/**
	 * 菜单名称
	 */
	@Schema(title = "菜单名称", description = "菜单名称")
	private String title;

}
