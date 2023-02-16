package com.relaxed.boot.risk.rules.score;


import com.relaxed.boot.risk.model.vo.RiskRuleVO;
import com.relaxed.boot.risk.rules.EvaluateContext;
import com.relaxed.boot.risk.rules.EvaluateReport;

import java.math.BigDecimal;

/**
 * @author Yakir
 * @Topic RiskScoreHandler
 * @Description 风控 评分决策处理器
 * @date 2021/9/9 16:51
 * @Version 1.0
 */
public interface RiskScoreHandler {

	/**
	 * 风控评分处理器 计算当前规则的评分
	 * @author yakir
	 * @date 2021/9/9 16:52
	 * @param evaluateContext
	 * @param evaluateReport
	 * @param ruleVO
	 * @return java.math.BigDecimal
	 */
	BigDecimal computeRule(EvaluateContext evaluateContext, EvaluateReport evaluateReport, RiskRuleVO ruleVO);

}
