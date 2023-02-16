package com.relaxed.boot.risk.rules.config;


import com.relaxed.boot.risk.rules.RiskEvaluate;
import com.relaxed.boot.risk.rules.RiskEvaluateChain;
import com.relaxed.boot.risk.rules.extractor.FieldExtractor;
import com.relaxed.boot.risk.rules.extractor.SimpleFieldExtractor;
import com.relaxed.boot.risk.rules.score.DefaultRiskScoreHandle;
import com.relaxed.boot.risk.rules.score.RiskScoreHandler;
import com.relaxed.boot.risk.rules.script.RuleScriptHandler;
import com.relaxed.boot.risk.rules.script.groovy.GroovyScriptHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Yakir
 * @Topic RiskEvaluateConfiguration
 * @Description
 * @date 2021/8/29 18:05
 * @Version 1.0
 */

@Configuration(proxyBeanMethods = false)
public class RiskEvaluateConfiguration {

	/**
	 * 风控过滤器链
	 * @author yakir
	 * @date 2021/8/29 18:09
	 * @param customizerList
	 * @return com.relaxed.common.risk.engine.rules.RiskEvaluateChain
	 */
	@Bean
	@ConditionalOnMissingBean
	public RiskEvaluateChain riskEvaluateChain(ObjectProvider<RiskEvaluateBuilderCustomizer> customizerList,
											   ObjectProvider<RiskEvaluate> riskEvaluates) {
		RiskEvaluateChain.Builder riskEvaluateChainBuilder = RiskEvaluateChain.builder();
		// load user defined
		customizerList.orderedStream().forEach(
				riskEvaluateBuilderCustomizer -> riskEvaluateBuilderCustomizer.customize(riskEvaluateChainBuilder));
		// load system eval
		riskEvaluates.orderedStream().forEach(riskEvaluate -> riskEvaluateChainBuilder.riskEvaluate(riskEvaluate));
		return riskEvaluateChainBuilder.build();
	}

	/**
	 * 规则脚本处理器
	 * @author yakir
	 * @date 2021/8/30 12:42
	 * @return com.relaxed.common.risk.engine.rules.script.RuleScriptHandler
	 */
	@Bean
	@ConditionalOnMissingBean
	public RuleScriptHandler ruleScriptHandler() {
		return new GroovyScriptHandler();
	}


	/**
	 * 字段提取器
	 * @author yakir
	 * @date 2021/8/30 13:37
	 * @return com.relaxed.common.risk.engine.rules.extractor.FieldExtractor
	 */
	@Bean
	@ConditionalOnMissingBean
	public FieldExtractor fieldExtractor() {
		return new SimpleFieldExtractor();
	}

	/**
	 * 风控评分处理器
	 * @author yakir
	 * @date 2021/9/9 16:54
	 * @param fieldExtractor
	 * @return com.relaxed.common.risk.engine.rules.score.RiskScoreHandler
	 */
	@Bean
	@ConditionalOnMissingBean
	public RiskScoreHandler riskScoreHandler(FieldExtractor fieldExtractor) {
		return new DefaultRiskScoreHandle(fieldExtractor);
	}



}
