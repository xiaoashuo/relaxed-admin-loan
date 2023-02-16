package com.relaxed.boot.risk.rules.executor;


import com.relaxed.boot.risk.enums.RiskActivationEnum;
import com.relaxed.boot.risk.enums.RiskEnum;
import com.relaxed.boot.risk.enums.RiskRuleEnum;
import com.relaxed.boot.risk.manage.RiskActivationManage;
import com.relaxed.boot.risk.manage.RiskRuleManage;
import com.relaxed.boot.risk.model.dto.Hit;
import com.relaxed.boot.risk.model.dto.Risk;
import com.relaxed.boot.risk.model.vo.RiskActivationVO;
import com.relaxed.boot.risk.model.vo.RiskModelVO;
import com.relaxed.boot.risk.model.vo.RiskRuleVO;
import com.relaxed.boot.risk.rules.AbstractRiskEvaluate;
import com.relaxed.boot.risk.rules.EvaluateContext;
import com.relaxed.boot.risk.rules.EvaluateReport;
import com.relaxed.boot.risk.rules.score.RiskScoreHandler;
import com.relaxed.common.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yakir
 * @Topic ActivationRiskEvaluate
 * @Description 策略评估 综合计分 决策+评分卡
 * @date 2021/8/29 17:47
 * @Version 1.0
 */
@Order(3)
@Component
@RequiredArgsConstructor
@Slf4j
public class ActivationRiskEvaluate extends AbstractRiskEvaluate {

	private static final String ACTIVATIONS = "activations";

	private final RiskActivationManage riskActivationManage;

	private final RiskRuleManage riskRuleManage;

	private final RiskScoreHandler riskScoreHandler;


	@Override
	public String getName() {
		return ACTIVATIONS;
	}

	@Override
	public boolean doEval(EvaluateContext evaluateContext, EvaluateReport evaluateReport) {
		Map<String, Object> extParam = new HashMap<>(8);
		// 具体击中规则列表 所有的击中规则
		Map<String, List<Hit>> hitRuleList = new HashMap<>();
		// 具体击中规则map 详细记录每条 key 规则
		Map<String, Map<String, Hit>> hitRulesMap = new HashMap<>();
		extParam.put("hitRuleList", hitRuleList);
		extParam.put("hitRuleMap", hitRulesMap);
		// 基础参数信息
		RiskModelVO modelVo = evaluateContext.getModelVo();
		// 评估决策列表
		List<RiskActivationVO> activationVOS = riskActivationManage.listByModelId(modelVo.getId());

		for (RiskActivationVO activationVO : activationVOS) {
			if (!RiskActivationEnum.StatusEnum.ENABLE.getStatus().equals(modelVo.getStatus())) {
				continue;
			}
			// 当前activations下的击中规则 与列表
			ArrayList<Hit> currentActivationHitList = new ArrayList<>();
			HashMap<String, Hit> currentActivationHitMap = new HashMap<>();
			hitRuleList.put(activationVO.getActivationName(), currentActivationHitList);
			hitRulesMap.put(activationVO.getActivationName(), currentActivationHitMap);

			// 获取当前策略 下的所有规则脚本
			List<RiskRuleVO> ruleVOS = riskRuleManage.listRule(activationVO.getId());
			// 当前策略评分 总分
			BigDecimal sum = BigDecimal.ZERO;
			// 遍历所有计分规则
			for (RiskRuleVO ruleVO : ruleVOS) {
				if (!RiskRuleEnum.StatusEnum.ENABLE.getStatus().equals(ruleVO.getStatus())) {
					continue;
				}
				String scripts = ruleVO.getScripts();
				String scriptEntry = ruleVO.getRuleScriptEntry();
				boolean match = super.checkScript(scripts, scriptEntry, evaluateContext, evaluateReport);
				// 不通过检查脚本规则 则当前规则不进行计算
				if (!match) {
					continue;
				}
				// 规则匹配 计算score
				BigDecimal score = riskScoreHandler.computeRule(evaluateContext, evaluateReport, ruleVO);
				sum = sum.add(score);
				// hit detail
				Hit hit = new Hit();
				hit.setKey(ruleVO.getId().toString());
				hit.setValue(score.setScale(2, 4).doubleValue());
				hit.setDesc(ruleVO.getLabel());
				currentActivationHitList.add(hit);
				currentActivationHitMap.put("rule_" + hit.getKey(), hit);
			}

			sum = sum.setScale(0, 4);
			// 计算风险级别
			BigDecimal high = new BigDecimal(activationVO.getHigh());
			BigDecimal median = new BigDecimal(activationVO.getMedian());
			BigDecimal bottom = new BigDecimal(activationVO.getBottom());
			// 计算结果
			RiskEnum riskEnum = RiskEnum.compute(sum, high, median, bottom);
			Risk risk = new Risk();
			risk.setRisk(riskEnum.name());
			risk.setScore(sum.intValue());
			extParam.put(activationVO.getActivationName(), risk);
		}
		evaluateReport.putEvaluateMap(this.getName(), extParam);
		return true;
	}

}
