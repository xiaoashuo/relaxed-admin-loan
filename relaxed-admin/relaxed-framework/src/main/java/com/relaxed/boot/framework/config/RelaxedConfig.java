package com.relaxed.boot.framework.config;

import com.relaxed.common.core.util.WebUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yakir
 * @Topic RelaxedConfig
 * @Description
 * @date 2022/11/27 12:37
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "sys")
public class RelaxedConfig {

	/** 上传路径 */
	private static String profile;

	public static String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		RelaxedConfig.profile = profile;
	}

	/**
	 * 获取完整的请求路径，包括：域名，端口，上下文访问路径
	 * @return 服务地址
	 */
	public static String getUrl() {
		HttpServletRequest request = WebUtils.getRequest();
		return getDomain(request);
	}

	public static String getDomain(HttpServletRequest request) {
		StringBuffer url = request.getRequestURL();
		String contextPath = request.getServletContext().getContextPath();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
	}

}
