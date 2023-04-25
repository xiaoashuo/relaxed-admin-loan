package com.relaxed.boot.risk.config.admin;

import com.relaxed.boot.risk.core.event.listener.RiskRuleChangeListener;
import com.relaxed.boot.risk.service.RiskRuleHistoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yakir
 * @Topic EventListenerConfiguration
 * @Description
 * @date 2021/9/24 15:08
 * @Version 1.0
 */
@Configuration
public class EventListenerConfiguration {

	/**
	 * 规则改变 监听事件
	 * @author yakir
	 * @date 2021/9/24 15:09
	 * @param ruleHistoryService
	 * @return com.relaxed.common.risk.admin.listener.RuleChangeListener
	 */
	@Bean
	public RiskRuleChangeListener ruleChangeListener(RiskRuleHistoryService ruleHistoryService) {
		return new RiskRuleChangeListener(ruleHistoryService);
	}

}
