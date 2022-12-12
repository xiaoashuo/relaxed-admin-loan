package com.relaxed.boot.auth.domain;

import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Collection;

/**
 * @author Yakir
 * @Topic SysUserDTO
 * @Description
 * @date 2022/8/1 17:08
 * @Version 1.0
 */
@Data
@Schema(title = "用户信息")
public class SysUserInfoDTO {

	/**
	 * 用户基本信息
	 */
	@Schema(title = "用户基本信息")
	private SysUser sysUser;

	/**
	 * 权限标识集合
	 */
	@Schema(title = "权限标识集合")
	private Collection<String> permissions;

	/**
	 * 角色标识集合
	 */
	@Schema(title = "角色标识集合")
	private Collection<String> roleCodes;

	/**
	 * 菜单对象集合
	 */
	@Schema(title = "菜单对象集合")
	private Collection<SysMenu> menus;

	/**
	 * 角色对象集合
	 */
	@Schema(title = "角色对象集合")
	private Collection<SysRole> roles;

}
