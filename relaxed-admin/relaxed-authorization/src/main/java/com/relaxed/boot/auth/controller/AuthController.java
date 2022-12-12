package com.relaxed.boot.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.relaxed.common.model.result.R;
import com.relaxed.common.model.result.SysResultCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yakir
 * @Topic AuthController
 * @Description
 * @date 2022/11/15 10:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Tag(name = "用户认证模块")
public class AuthController {

	private final TokenStore tokenStore;

	private final ApplicationEventPublisher publisher;

	/**
	 * 退出token
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public R<?> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return R.failed(SysResultCode.FORBIDDEN, "退出失败，token 为空");
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return R.failed(SysResultCode.FORBIDDEN, "退出失败，token 无效");
		}
		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
		// 清空access token
		tokenStore.removeAccessToken(accessToken);
		// // 清空 refresh token
		// OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
		// tokenStore.removeRefreshToken(refreshToken);

		// 发布用户登出事件
		publisher.publishEvent(new LogoutSuccessEvent(auth2Authentication));

		return R.ok();
	}

}
