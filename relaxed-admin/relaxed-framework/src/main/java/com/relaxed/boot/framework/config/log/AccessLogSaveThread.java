package com.relaxed.boot.framework.config.log;

import com.relaxed.boot.biz.system.service.LogAccessService;
import com.relaxed.boot.common.system.model.entity.LogAccess;
import com.relaxed.common.core.thread.AbstractBlockQueueThread;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yakir
 * @Topic AccessLogSaveThread
 * @Description
 * @date 2022/2/25 9:40
 * @Version 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AccessLogSaveThread extends AbstractBlockQueueThread<LogAccess> {

	private final LogAccessService accessLogService;

	/**
	 * 线程启动时的日志打印
	 */
	@Override
	public void init() {
		log.info("后台访问日志存储线程已启动===");
	}

	/**
	 * 错误日志打印
	 * @param ex 错误堆栈
	 * @param list 后台访问日志列表
	 */
	@Override
	public void errorHandle(Throwable ex, List<LogAccess> list) {
		log.error("后台访问日志记录异常, [msg]:{}, [data]:{}", ex.getMessage(), list);
	}

	/**
	 * 数据保存
	 * @param list 后台访问日志列表
	 */
	@Override
	public void process(List<LogAccess> list) {
		accessLogService.saveBatch(list);
	}

}
