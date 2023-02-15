package com.relaxed.boot.risk.core.event;

import com.relaxed.boot.risk.model.entity.RiskRuleHistory;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Yakir
 * @Topic RuleChangeEvent
 * @Description
 * @date 2021/9/24 15:04
 * @Version 1.0
 */

@Getter
public class RiskRuleChangeEvent extends ApplicationEvent {

	/**
	 * 规则
	 */
	private final RiskRuleHistory ruleHistory;

	public RiskRuleChangeEvent(RiskRuleHistory ruleHistory) {
		super(ruleHistory);
		this.ruleHistory = ruleHistory;
	}

}
