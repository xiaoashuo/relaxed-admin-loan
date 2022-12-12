package com.relaxed.boot.auth.config;

import com.relaxed.boot.auth.checker.DefaultRoleChecker;
import com.relaxed.boot.auth.checker.RoleChecker;
import com.relaxed.boot.auth.component.CustomTokenEnhancer;
import com.relaxed.boot.auth.config.filter.IgnoreUrlCheckFilter;
import com.relaxed.boot.auth.config.mobile.CustomCaptchaValidator;
import com.relaxed.boot.auth.config.mobile.CustomSmsCodeValidator;
import com.relaxed.boot.auth.constants.UserConstant;
import com.relaxed.boot.auth.service.AuthService;
import com.relaxed.boot.auth.service.SysUserDetailsServiceImpl;
import com.relaxed.boot.auth.service.Tees2UserService;
import com.relaxed.boot.biz.system.service.SysUserService;
import com.relaxed.oauth2.auth.configurer.OAuth2ClientConfigurer;
import com.relaxed.oauth2.auth.extension.ExtendUserDetailsService;

import com.relaxed.oauth2.auth.extension.PreValidator;
import com.relaxed.oauth2.auth.functions.RetriveUserFunction;
import com.relaxed.oauth2.auth.handler.AuthorizationInfoHandle;
import com.relaxed.oauth2.auth.util.PreMethodInterceptor;
import com.relaxed.oauth2.auth.util.ProxyFactory;

import com.relaxed.oauth2.resource.properties.ExtendResourceServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yakir
 * @Topic AuthConfig
 * @Description
 * @date 2022/7/24 14:50
 * @Version 1.0
 */
@RequiredArgsConstructor
@Configuration
public class AuthConfig {

	/**
	 * password 模式下，密码入参要求 AES 加密。 在进入令牌端点前，通过过滤器进行解密处理。
	 * @param extendProperties 安全配置相关
	 * @return FilterRegistrationBean<LoginPasswordDecoderFilter>
	 */

	@Bean
	public FilterRegistrationBean<IgnoreUrlCheckFilter> ignoreUrlCheckFilterFilter(
			ExtendResourceServerProperties extendProperties) {
		List<String> filterUrlList = Optional.ofNullable(extendProperties.getIgnoreUrls())
				.map(e -> e.stream().map(item -> item.replaceAll("\\*\\*", "\\*")).collect(Collectors.toList()))
				.orElse(new ArrayList<>());
		FilterRegistrationBean<IgnoreUrlCheckFilter> bean = new FilterRegistrationBean<>(new IgnoreUrlCheckFilter());
		// 确保 在spring security chain 前面 spring security chain filter 默认值为-100
		bean.setOrder(-200);
		bean.addUrlPatterns(filterUrlList.toArray(new String[0]));
		return bean;
	}

	/**
	 * redis 令牌存储
	 * @return
	 */
	@Bean
	public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		redisTokenStore.setPrefix("pc:");
		return redisTokenStore;
	}

	@Bean
	public RoleChecker roleChecker() {
		return new DefaultRoleChecker();
	}

	/**
	 * token增强器
	 * @return
	 */
	@Bean
	public CustomTokenEnhancer extendTokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	@Bean
	public PreValidator smsCodeValidator() {
		return new CustomSmsCodeValidator();
	}

	@Bean
	public PreValidator customCaptchaValidator() {
		return new CustomCaptchaValidator();
	}

	/**
	 * 授权信息处理器
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public AuthorizationInfoHandle authorizationInfoHandle() {
		return new AuthorizationInfoHandle().client("resource", new Tees2UserService())
				.grantType("password", new RetriveUserFunction() {
					@Override
					public <T extends Authentication> UserDetails retrive(T authentication,
							UserDetailsService userDetailsService) {
						String name = authentication.getName();
						return userDetailsService.loadUserByUsername(name);
					}
				}).grantType("sms_code", new RetriveUserFunction() {
					@Override
					public <T extends Authentication> UserDetails retrive(T authentication,
							UserDetailsService userDetailsService) {
						String name = authentication.getName();
						ExtendUserDetailsService extendUserDetailsService = (ExtendUserDetailsService) userDetailsService;
						return extendUserDetailsService.loginByMobile(name);
					}
				});
	}

	@Bean
	public UserDetailsService userService(AuthService authService, AuthorizationInfoHandle authorizationInfoHandle) {
		// UserService userService = new UserService();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SysUserDetailsServiceImpl.class);
		enhancer.setCallback(new PreMethodInterceptor(authorizationInfoHandle));

		return (SysUserDetailsServiceImpl) enhancer.create(new Class[] { AuthService.class },
				new Object[] { authService });
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// 在 springmvc 5.3 版本之后，跨域来源使用 * 号进行匹配的方式进行调整
		config.addAllowedOriginPattern(UserConstant.ALL);
		config.addAllowedHeader(UserConstant.ALL);
		config.addAllowedMethod(UserConstant.ALL);
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
