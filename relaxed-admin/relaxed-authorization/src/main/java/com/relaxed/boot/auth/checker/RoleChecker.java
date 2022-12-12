package com.relaxed.boot.auth.checker;

import com.relaxed.boot.common.system.model.entity.SysUser;

/**
 * @author Yakir
 * @Topic RoleChecker
 * @Description
 * @date 2022/8/2 16:24
 * @Version 1.0
 */
public interface RoleChecker {

	/**
	 * 是否为管理用户
	 * @date 2022/8/2 16:25
	 * @param sysUser
	 * @return boolean
	 */
	boolean isAdminUser(SysUser sysUser);

}
