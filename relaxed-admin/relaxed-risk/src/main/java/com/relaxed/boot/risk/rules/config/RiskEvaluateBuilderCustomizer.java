package com.relaxed.boot.risk.rules.config;


import com.relaxed.boot.risk.rules.RiskEvaluateChain;

/**
 * @author Yakir
 * @Topic RiskEvaluateBuilderCustomizer
 * @Description
 * @date 2021/8/29 18:07
 * @Version 1.0
 */
@FunctionalInterface
public interface RiskEvaluateBuilderCustomizer {

	/**
	 * 填充过滤器链
	 * @author yakir
	 * @date 2021/8/29 18:07
	 * @param riskEvaluateChainBuilder
	 */
	void customize(RiskEvaluateChain.Builder riskEvaluateChainBuilder);

}
