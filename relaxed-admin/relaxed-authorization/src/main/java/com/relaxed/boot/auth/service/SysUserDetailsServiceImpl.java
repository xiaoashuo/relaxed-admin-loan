package com.relaxed.boot.auth.service;

import cn.hutool.core.collection.CollectionUtil;

import com.relaxed.boot.auth.constants.UserConstant;
import com.relaxed.boot.auth.domain.SysUserDetails;
import com.relaxed.boot.auth.domain.SysUserInfoDTO;
import com.relaxed.boot.biz.system.service.SysUserService;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.oauth2.auth.extension.ExtendUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Yakir
 * @Topic SysUserDetailsServiceImpl
 * @Description
 * @date 2022/8/1 17:12
 * @Version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class SysUserDetailsServiceImpl implements ExtendUserDetailsService {

	private final AuthService authService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = authService.getByUsername(username);
		if (sysUser == null) {
			log.error("登陆：用户名错误，用户名：{}", username);
			throw new UsernameNotFoundException("username error!");
		}
		SysUserInfoDTO sysUserInfoDTO = authService.findUserInfo(sysUser);
		return getUserDetailsByUserInfo(sysUserInfoDTO);
	}

	@Override
	public UserDetails loginByMobile(String mobileNo) {
		SysUser sysUser = authService.getByMobileNo(mobileNo);
		if (sysUser == null) {
			log.error("登陆：手机号错误，手机号：{}", mobileNo);
			throw new UsernameNotFoundException("mobile no error!");
		}
		SysUserInfoDTO sysUserInfoDTO = authService.findUserInfo(sysUser);
		return getUserDetailsByUserInfo(sysUserInfoDTO);
	}

	private UserDetails getUserDetailsByUserInfo(SysUserInfoDTO sysUserInfoDTO) {
		SysUser sysUser = sysUserInfoDTO.getSysUser();
		Collection<String> roleCodes = sysUserInfoDTO.getRoleCodes();
		Collection<String> permissions = sysUserInfoDTO.getPermissions();
		Collection<String> dbAuthsSet = new HashSet<>();
		if (CollectionUtil.isNotEmpty(roleCodes)) {
			// 获取角色
			dbAuthsSet.addAll(roleCodes);
			// 获取资源
			dbAuthsSet.addAll(permissions);

		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils
				.createAuthorityList(dbAuthsSet.toArray(new String[0]));

		// 默认将角色和权限放入属性中
		HashMap<String, Object> attributes = new HashMap<>(8);
		attributes.put(UserConstant.ROLE_CODES, roleCodes);
		attributes.put(UserConstant.PERMISSIONS, permissions);
		return new SysUserDetails()
				.setUserId(sysUser.getUserId())
				.setUsername(sysUser.getUsername())
				.setNickname(sysUser.getNickname())
				.setPassword(sysUser.getPassword())
				.setPhone(sysUser.getPhone())
				.setAvatar(sysUser.getAvatar())
				.setEmail(sysUser.getEmail())
				.setStatus(sysUser.getStatus())
				.setCreatedTime(sysUser.getCreatedTime())
				.setAuthorities(authorities)
				.setAttributes(attributes)
				;
	}

}
