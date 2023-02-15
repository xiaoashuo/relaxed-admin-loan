package com.relaxed.boot.risk.rules;

/**
 * @author Yakir
 * @Topic RiskEvaluate
 * @Description 评估抽象
 * @date 2021/8/29 17:13
 * @Version 1.0
 */
public interface RiskEvaluate {

	/**
	 * 风控评估名称
	 * @author yakir
	 * @date 2021/8/31 14:46
	 * @return java.lang.String
	 */
	String getName();

	/**
	 * 风控评估
	 * @author yakir
	 * @date 2021/8/29 17:14
	 * @param evaluateContext 评估上下文
	 * @param evaluateReport 评估报告
	 * @return boolean true 走入下个责任链
	 */
	boolean doEval(EvaluateContext evaluateContext, EvaluateReport evaluateReport);

}
