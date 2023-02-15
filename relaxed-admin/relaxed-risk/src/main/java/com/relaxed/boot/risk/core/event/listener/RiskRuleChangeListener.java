package com.relaxed.boot.risk.core.event.listener;

import com.relaxed.boot.risk.core.event.RiskRuleChangeEvent;
import com.relaxed.boot.risk.service.RiskRuleHistoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * @author Yakir
 * @Topic RuleChangeListener
 * @Description
 * @date 2021/9/24 15:06
 * @Version 1.0
 */
@RequiredArgsConstructor
public class RiskRuleChangeListener {

	private final RiskRuleHistoryService ruleHistoryService;

	@Async
	@EventListener(RiskRuleChangeEvent.class)
	public void onDictChangeEvent(RiskRuleChangeEvent event) {
		ruleHistoryService.save(event.getRuleHistory());
	}

}
