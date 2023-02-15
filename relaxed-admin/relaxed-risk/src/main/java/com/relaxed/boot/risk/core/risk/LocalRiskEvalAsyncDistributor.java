package com.relaxed.boot.risk.core.risk;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author Yakir
 * @Topic LocalRiskEvalAsyncDistributor
 * @Description
 * @date 2021/9/26 9:23
 * @Version 1.0
 */
@Slf4j
public class LocalRiskEvalAsyncDistributor implements RiskEvalAsyncDistributor {

	@Override
	public void distribute(String modelGuid, String reqId, Map jsonInfo) {
		// 选择落库 或rocketmq 或es等等 然后定时取出任务 进行风控评估执行
		log.info("异步评估执行参数,模型{},请求{},参数{}", modelGuid, reqId, jsonInfo);
	}

}
