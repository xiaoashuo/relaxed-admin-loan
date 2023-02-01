package com.relaxed.boot.auth.component;

import com.relaxed.boot.auth.constants.UserConstant;
import com.relaxed.boot.auth.domain.SysUserDetails;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.boot.framework.config.RelaxedConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yakir
 * @Topic CustomTokenEnhancer
 * @Description
 * @date 2022/8/1 18:00
 * @Version 1.0
 */
@Slf4j
public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		log.info("进入");
		Authentication userAuthentication = authentication.getUserAuthentication();
		if (userAuthentication == null) {
			return accessToken;
		}

		Object principal = userAuthentication.getPrincipal();
		if (principal instanceof SysUserDetails) {
			SysUserDetails sysUserDetails = (SysUserDetails) principal;
			// token 附属信息
			Map<String, Object> additionalInfo = new HashMap<>(8);

			// 用户基本信息
			SysUser sysUserInfo = getSysUserInfo(sysUserDetails);
			additionalInfo.put(UserConstant.INFO, sysUserInfo);

			// 默认在登陆时只把角色和权限的信息返回
			Map<String, Object> resultAttributes = new HashMap<>(2);
			Map<String, Object> attributes = sysUserDetails.getAttributes();
			resultAttributes.put(UserConstant.ROLE_CODES, attributes.get(UserConstant.ROLE_CODES));
			resultAttributes.put(UserConstant.PERMISSIONS, attributes.get(UserConstant.PERMISSIONS));
			additionalInfo.put(UserConstant.ATTRIBUTES, resultAttributes);
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		}
		return accessToken;
	}

	/**
	 * 根据 User 对象获取 SysUserInfo
	 * @param sysUserDetails User
	 * @return SysUserInfo
	 */
	public SysUser getSysUserInfo(SysUserDetails sysUserDetails) {
		SysUser sysUserInfo = new SysUser();
		sysUserInfo.setUserId(sysUserDetails.getUserId());
		sysUserInfo.setUsername(sysUserDetails.getUsername());
		sysUserInfo.setNickname(sysUserDetails.getNickname());
		sysUserInfo.setStatus(sysUserDetails.getStatus());
		sysUserInfo.setAvatar(RelaxedConfig.getUrl()+sysUserDetails.getAvatar());
		sysUserInfo.setEmail(sysUserDetails.getEmail());
		sysUserInfo.setPhone(sysUserDetails.getPhone());
		sysUserInfo.setCreatedTime(sysUserDetails.getCreatedTime());
		return sysUserInfo;
	}

}
