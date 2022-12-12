package com.relaxed.boot.auth.config.filter;

import cn.hutool.core.collection.ListUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yakir
 * @Topic IgnoreUrlCheckFilter
 * @Description
 * @date 2022/8/24 17:01
 * @Version 1.0
 */
public class IgnoreUrlCheckFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ModifyHeaderRequestWrapper authorization = new ModifyHeaderRequestWrapper(request,
				ListUtil.toList("Authorization"));
		filterChain.doFilter(authorization, response);
	}

}
