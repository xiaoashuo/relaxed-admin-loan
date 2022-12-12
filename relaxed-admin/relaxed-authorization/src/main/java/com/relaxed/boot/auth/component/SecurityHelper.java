package com.relaxed.boot.auth.component;

import com.relaxed.boot.auth.domain.SysUserDetails;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.oauth2.common.util.SecurityUtils;
import org.springframework.security.core.Authentication;

/**
 * @author Yakir
 * @Topic SecurityHelper
 * @Description
 * @date 2022/11/11 14:07
 * @Version 1.0
 */
public class SecurityHelper extends SecurityUtils {

	public static SysUserDetails getUser(Authentication authentication) {
		if (authentication == null) {
			return null;
		}
		else {
			Object principal = authentication.getPrincipal();
			return principal instanceof SysUserDetails ? (SysUserDetails) principal : null;
		}
	}

	public static SysUserDetails getUser() {
		Authentication authentication = getAuthentication();
		return getUser(authentication);
	}

}
