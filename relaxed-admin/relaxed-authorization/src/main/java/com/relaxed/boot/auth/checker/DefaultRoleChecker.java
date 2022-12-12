package com.relaxed.boot.auth.checker;

import com.relaxed.boot.common.system.model.entity.SysUser;

/**
 * @author Yakir
 * @Topic DefaultRoleChecker
 * @Description
 * @date 2022/8/2 16:35
 * @Version 1.0
 */
public class DefaultRoleChecker implements RoleChecker {

	@Override
	public boolean isAdminUser(SysUser sysUser) {
		String username = sysUser.getUsername();
		if ("admin".equals(username)) {
			return true;
		}
		return false;
	}

}
