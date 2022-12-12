package com.relaxed.boot.framework.config.log;

import com.relaxed.boot.biz.system.service.LogOperationService;
import com.relaxed.boot.common.system.model.entity.LogOperation;
import com.relaxed.common.core.thread.AbstractBlockQueueThread;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yakir
 * @Topic CallRecordThread
 * @Description
 * @date 2021/7/12 11:18
 * @Version 1.0
 */
@RequiredArgsConstructor
@Component
public class OptionLogSaveThread extends AbstractBlockQueueThread<LogOperation> {

	private final LogOperationService logOperationService;

	/**
	 * task size
	 */
	@Value("${threadConfig.batchSize:500}")
	private Integer batchSize = 500;

	/**
	 * task timeout ms
	 */
	@Value("${threadConfig.batchTimeoutMs:30000}")
	private Long batchTimeoutMs = 30000L;

	/**
	 * queue take timeout ms
	 */
	@Value("${threadConfig.pollTimeoutMs:5000}")
	private Long pollTimeoutMs = 5000L;

	@Override
	public void process(List<LogOperation> list) {
		logOperationService.insertBatchSomeColumn(list);
	}

	@Override
	public int getBatchSize() {
		return batchSize;
	}

	@Override
	public long getBatchTimeout() {
		return batchTimeoutMs;
	}

	@Override
	public long getPollTimeoutMs() {
		return pollTimeoutMs;
	}

}
