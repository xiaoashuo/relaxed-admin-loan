package com.relaxed.boot.framework.config.log;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;

import com.relaxed.boot.common.system.model.entity.LogAccess;
import com.relaxed.common.core.util.IpUtils;
import com.relaxed.common.log.access.handler.AccessLogHandler;
import com.relaxed.common.log.access.utils.LogUtils;

import com.relaxed.common.log.mdc.constant.LogConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author Yakir
 * @Topic CustomAccessLogHandler
 * @Description
 * @date 2022/2/25 9:39
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class CustomAccessLogHandler implements AccessLogHandler<LogAccess> {

	private static final String APPLICATION_JSON = "application/json";

	private final AccessLogSaveThread accessLogSaveThread;

	@Override
	public LogAccess prodLog(HttpServletRequest request, HttpServletResponse response, Long time, Throwable throwable) {
		Object matchingPatternAttr = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		String matchingPattern = matchingPatternAttr == null ? "" : String.valueOf(matchingPatternAttr);
		// @formatter:off
        String uri = URLUtil.getPath(request.getRequestURI());
		LogAccess accessLog = new LogAccess()
                .setTraceId(MDC.get(LogConstant.TRACE_ID))
                .setCreatedTime(LocalDateTime.now())
                .setTime(time)
                .setIp(IpUtils.getIpAddr(request))
                .setMethod(request.getMethod())
                .setUserAgent(request.getHeader("user-agent"))
                .setUri(uri)
                .setMatchingPattern(matchingPattern)
                .setErrorMsg(Optional.ofNullable(throwable).map(Throwable::getMessage).orElse(""))
                .setHttpStatus(response.getStatus());
        // @formatter:on
		// 参数获取
		String params = getParams(request);
		accessLog.setReqParams(params);

		// 非文件上传请求，记录body，用户改密时不记录body
		// TODO 使用注解控制此次请求是否记录body，更方便个性化定制
		if (!LogUtils.isMultipartContent(request) && !"/system/user/pass/{userId}".equals(matchingPattern)) {
			accessLog.setReqBody(LogUtils.getRequestBody(request));
		}

		// 只记录响应头为 application/json 的返回数据
		// 后台日志对于分页数据请求，不记录返回值
		if (!uri.endsWith("/page") && response.getContentType() != null
				&& response.getContentType().contains(APPLICATION_JSON)) {
			accessLog.setResult(LogUtils.getResponseBody(request, response));
		}

		// 如果登陆用户 则记录用户名和用户id
		// Optional.ofNullable(SecurityUtils.getUser()).ifPresent(x -> {
		// // accessLog.setUserId(x.getUserId());
		// // accessLog.setUsername(x.getUsername());
		// });
		log.info("请求记录: {}", accessLog);

		return accessLog;
	}

	@Override
	public void saveLog(LogAccess accessLog) {
		// 异步保存
		accessLogSaveThread.put(accessLog);
	}

	/**
	 * 获取参数信息
	 * @param request 请求信息
	 * @return 请求参数
	 */
	public String getParams(HttpServletRequest request) {
		String params;
		try {
			Map<String, String[]> parameterMap = request.getParameterMap();
			// for (String paramKey : needDesensitizeParams) {
			// String[] values = parameterMap.get(paramKey);
			// if (values != null && values.length != 0) {
			// String value =
			// DesensitizationHandlerHolder.getRegexDesensitizationHandler().handle(values[0],
			// RegexDesensitizationTypeEnum.ENCRYPTED_PASSWORD);
			// parameterMap.put(paramKey, new String[] { value });
			// }
			// }
			params = JSONUtil.toJsonStr(parameterMap);
		}
		catch (Exception e) {
			params = "记录参数异常";
			log.error("[prodLog]，参数获取序列化异常", e);
		}
		return params;
	}

}
