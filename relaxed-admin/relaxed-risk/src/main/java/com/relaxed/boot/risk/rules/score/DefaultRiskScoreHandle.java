package com.relaxed.boot.risk.rules.score;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import com.relaxed.boot.risk.model.vo.RiskRuleVO;
import com.relaxed.boot.risk.rules.EvaluateContext;
import com.relaxed.boot.risk.rules.EvaluateReport;
import com.relaxed.boot.risk.rules.extractor.FieldExtractor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Yakir
 * @Topic DefaultRiskScoreHandle
 * @Description
 * @date 2021/9/9 16:53
 * @Version 1.0
 */
@RequiredArgsConstructor
public class DefaultRiskScoreHandle implements RiskScoreHandler {

	private final FieldExtractor fieldExtractor;

	@Override
	public BigDecimal computeRule(EvaluateContext evaluateContext, EvaluateReport evaluateReport, RiskRuleVO ruleVO) {
		// 初始得分
		BigDecimal initScore = new BigDecimal(ruleVO.getInitScore());
		BigDecimal extra = BigDecimal.ZERO;
		// 最大分数
		BigDecimal maxScore = new BigDecimal(ruleVO.getMax());
		BigDecimal score = initScore.add(extra);
		// 规则得分设置最大值. 若得分超出 最大分数 则同步为最大分数
		if (maxScore.compareTo(BigDecimal.ZERO) > 0 && score.compareTo(maxScore) > 0) {
			score = maxScore;
		}
		return score;
	}

}
