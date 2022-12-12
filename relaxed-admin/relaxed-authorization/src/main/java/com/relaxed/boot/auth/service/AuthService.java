package com.relaxed.boot.auth.service;

import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.auth.checker.RoleChecker;
import com.relaxed.boot.auth.domain.SysUserInfoDTO;
import com.relaxed.boot.biz.system.service.SysMenuService;
import com.relaxed.boot.biz.system.service.SysRoleService;
import com.relaxed.boot.biz.system.service.SysUserService;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.entity.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yakir
 * @Topic AuthService
 * @Description
 * @date 2022/11/11 11:05
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {

	private final SysUserService sysUserService;

	private final SysRoleService sysRoleService;

	private final SysMenuService sysMenuService;

	private final RoleChecker roleChecker;

	public SysUserInfoDTO findUserInfo(SysUser sysUser) {
		SysUserInfoDTO sysUserInfoDTO = new SysUserInfoDTO();
		sysUserInfoDTO.setSysUser(sysUser);
		List<SysRole> roleList;
		// 判断是否是管理员
		if (roleChecker.isAdminUser(sysUser)) {
			roleList = sysRoleService.list();
		}
		else {
			roleList = sysRoleService.listRoles(sysUser.getUserId());
		}

		// 设置角色标识
		Set<String> roleCodes = new HashSet<>();
		for (SysRole role : roleList) {
			roleCodes.add(role.getCode());
		}
		sysUserInfoDTO.setRoles(new HashSet<>(roleList));
		sysUserInfoDTO.setRoleCodes(roleCodes);
		// 设置权限列表（permission）
		Set<String> permissions = new HashSet<>();
		Set<SysMenu> menus = new HashSet<>();
		for (String roleCode : roleCodes) {
			List<SysMenu> sysMenuList = sysMenuService.listByRoleCode(roleCode);
			menus.addAll(sysMenuList);
			List<String> permissionList = sysMenuList.stream().map(SysMenu::getPermission).filter(StrUtil::isNotEmpty)
					.collect(Collectors.toList());
			permissions.addAll(permissionList);
		}
		sysUserInfoDTO.setMenus(menus);
		sysUserInfoDTO.setPermissions(permissions);
		return sysUserInfoDTO;
	}

	public SysUser getByUsername(String username) {
		return sysUserService.getByUsername(username);
	}

	public SysUser getByMobileNo(String mobileNo) {
		return sysUserService.getByMobileNo(mobileNo);
	}

}
