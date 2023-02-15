package com.relaxed.boot.risk.core.risk;

import java.util.Map;

/**
 * @author Yakir
 * @Topic EvaluateRiskAsyncHandler
 * @Description 风控评估异步处理器
 * @date 2021/9/26 9:20
 * @Version 1.0
 */
public interface RiskEvalAsyncDistributor {

	/**
	 * 异步任务分发
	 * @author yakir
	 * @date 2021/9/26 9:23
	 * @param modelGuid
	 * @param reqId
	 * @param jsonInfo
	 */
	void distribute(String modelGuid, String reqId, Map jsonInfo);

}
