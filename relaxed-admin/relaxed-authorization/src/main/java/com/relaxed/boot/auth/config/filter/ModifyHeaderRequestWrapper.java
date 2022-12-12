package com.relaxed.boot.auth.config.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Yakir
 * @Topic ModifyParamRequestWrapper
 * @Description
 * @date 2022/8/24 17:04
 * @Version 1.0
 */
public class ModifyHeaderRequestWrapper extends HttpServletRequestWrapper {

	private List<String> ignoreRequestHeader;

	/**
	 * Constructs a request object wrapping the given request.
	 * @param request the {@link HttpServletRequest} to be wrapped.
	 * @throws IllegalArgumentException if the request is null
	 */
	public ModifyHeaderRequestWrapper(HttpServletRequest request, List<String> ignoreRequestHeader) {
		super(request);
		this.ignoreRequestHeader = ignoreRequestHeader == null ? new ArrayList<>() : ignoreRequestHeader;
	}

	@Override
	public String getHeader(String name) {
		if (ignoreRequestHeader.contains(name)) {
			return null;
		}
		return super.getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		if (ignoreRequestHeader.contains(name)) {
			return Collections.emptyEnumeration();
		}
		return super.getHeaders(name);
	}

}
