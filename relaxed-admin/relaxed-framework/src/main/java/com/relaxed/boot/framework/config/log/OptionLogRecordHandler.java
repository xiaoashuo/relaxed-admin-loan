package com.relaxed.boot.framework.config.log;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.relaxed.boot.common.system.model.entity.LogOperation;
import com.relaxed.common.core.util.IpUtils;

import com.relaxed.common.core.util.WebUtils;
import com.relaxed.common.log.mdc.constant.LogConstant;

import com.relaxed.common.log.operation.annotation.Log;
import com.relaxed.common.log.operation.enums.LogStatusEnum;
import com.relaxed.common.log.operation.service.AbstractOperationLogHandler;
import com.relaxed.common.log.operation.service.OperationLogHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Yakir
 * @Topic OptionLogRecord
 * @Description
 * @date 2021/6/27 16:42
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class OptionLogRecordHandler extends AbstractOperationLogHandler<LogOperation> {

	private final OptionLogSaveThread operationLogHandler;

	@Override
	public LogOperation buildLog(Log log, ProceedingJoinPoint proceedingJoinPoint) {
		// 获取 Request

		HttpServletRequest request = WebUtils.getRequest();

		// @formatter:off
		LogOperation operationLog = new LogOperation()
				.setCreatedTime(LocalDateTime.now())
				.setIp(IpUtils.getIpAddr(request))
				.setMethod(request.getMethod())
				.setUserAgent(request.getHeader("user-agent"))
				.setUri(URLUtil.getPath(request.getRequestURI()))
				.setType(log.type())
				.setMsg(log.msg())
				.setParams(getParams(proceedingJoinPoint))
				.setTraceId(MDC.get(LogConstant.TRACE_ID));
		// @formatter:on

		// 操作用户
		return operationLog;
	}

	@Override
	public LogOperation fillExecutionInfo(LogOperation logOperation, ProceedingJoinPoint proceedingJoinPoint,
			long startTime, long endTime, Throwable throwable, boolean isSaveResult, Object executionResult) {

		// 操作用户
		return logOperation;
	}

	@Override
	public void handleLog(LogOperation logOperation) {
		// 异步保存
		operationLogHandler.put(logOperation);
	}

}
