package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 角色菜单表
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@TableName("sys_role_menu")
@Schema(title = "角色菜单表")
public class SysRoleMenu {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@Schema(title = "", description = "")
	private Long id;

	/**
	 * role code
	 */
	@Schema(title = "role code", description = "role code")
	private String roleCode;

	/**
	 * 菜单ID
	 */
	@Schema(title = "菜单ID", description = "菜单ID")
	private Integer menuId;

	public SysRoleMenu() {
	}

	public SysRoleMenu(String roleCode, Integer menuId) {
		this.roleCode = roleCode;
		this.menuId = menuId;
	}

}
