package com.relaxed.boot.risk.rules;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yakir
 * @Topic RiskEvaluateChain
 * @Description 风控评估链
 * @date 2021/8/29 17:18
 * @Version 1.0
 */
@Slf4j
public class RiskEvaluateChain {

	private List<RiskEvaluate> riskEvaluates = new ArrayList<>();

	public RiskEvaluateChain(List<RiskEvaluate> riskEvaluates) {
		this.riskEvaluates = riskEvaluates;
	}

	public void setRiskEvaluates(List<RiskEvaluate> riskEvaluates) {
		this.riskEvaluates = riskEvaluates;
	}

	public void addRiskEvaluate(RiskEvaluate riskEvaluate) {
		this.riskEvaluates.add(riskEvaluate);
	}

	/**
	 * 风控评估自动填充开始结束时间
	 * @author yakir
	 * @date 2021/8/29 17:59
	 * @param evaluateContext
	 * @param evaluateReport
	 * @return boolean
	 */
	public boolean eval(EvaluateContext evaluateContext, EvaluateReport evaluateReport) {

		evaluateReport.setStartTime(LocalDateTime.now());
		log.info("风控评估开始执行,执行参数{}", evaluateContext);
		boolean result = doEval(evaluateContext, evaluateReport);
		evaluateReport.setEndTime(LocalDateTime.now());
		log.info("风控评估结束执行,执行参数{},执行结果{}", evaluateContext, evaluateReport);
		return result;

	}

	/**
	 * 风控评估
	 * @author yakir
	 * @date 2021/8/29 17:36
	 * @param evaluateContext 评估上下文
	 * @param evaluateReport 评估报告
	 * @return boolean true 评估通过 false 评估未通过
	 */
	private boolean doEval(EvaluateContext evaluateContext, EvaluateReport evaluateReport) {
		for (int i = 0; i < this.riskEvaluates.size(); i++) {
			RiskEvaluate riskEvaluate = this.riskEvaluates.get(i);
			String name = riskEvaluate.getName();
			long start = System.currentTimeMillis();
			log.info("{}风险评估,开始时间{}", name, start);
			boolean result = riskEvaluate.doEval(evaluateContext, evaluateReport);
			long end = System.currentTimeMillis();
			long time = end - start;
			log.info("{}风险评估,结束时间{},耗时{} ms", name, end, time);
			evaluateReport.putPhaseTime(name, time);
			if (!result) {
				return false;
			}
		}
		return true;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Data
	public static class Builder {

		private List<RiskEvaluate> riskEvaluates = new ArrayList<>();

		private Builder() {
		}

		/**
		 * 添加风控评估控件
		 * @author yakir
		 * @date 2021/8/30 9:39
		 * @param riskEvaluate
		 * @return com.relaxed.common.risk.engine.rules.RiskEvaluateChain.Builder
		 */
		public Builder riskEvaluate(RiskEvaluate riskEvaluate) {
			this.riskEvaluates.add(riskEvaluate);
			return this;
		}

		/**
		 * 指定位置添加风控评估控件
		 * @author yakir
		 * @date 2021/8/30 9:39
		 * @param index
		 * @param riskEvaluate
		 * @return com.relaxed.common.risk.engine.rules.RiskEvaluateChain.Builder
		 */
		public Builder riskEvaluate(Integer index, RiskEvaluate riskEvaluate) {
			this.riskEvaluates.add(index, riskEvaluate);
			return this;
		}

		/**
		 * 构建风控评估链
		 * @author yakir
		 * @date 2021/8/30 9:40
		 * @return com.relaxed.common.risk.engine.rules.RiskEvaluateChain
		 */
		public RiskEvaluateChain build() {
			return new RiskEvaluateChain(this.riskEvaluates);
		}

	}

}
