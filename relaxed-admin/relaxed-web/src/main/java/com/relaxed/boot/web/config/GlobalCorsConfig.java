package com.relaxed.boot.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Yakir
 * @Topic GlobalCorsConfig
 * @Description
 * @date 2022/11/14 13:37
 * @Version 1.0
 */
@Configuration(proxyBeanMethods = false)
public class GlobalCorsConfig {

	private static final String ALL_ORIGIN = "*";

	/**
	 * 允许跨域调用的过滤器
	 */
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		// 允许所有域名进行跨域调用
		config.addAllowedOriginPattern(ALL_ORIGIN);
		// 允许跨越发送cookie
		config.setAllowCredentials(true);
		// 放行全部原始头信息
		config.addAllowedHeader(ALL_ORIGIN);
		// 允许所有请求方法跨域调用
		config.addAllowedMethod(ALL_ORIGIN);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
