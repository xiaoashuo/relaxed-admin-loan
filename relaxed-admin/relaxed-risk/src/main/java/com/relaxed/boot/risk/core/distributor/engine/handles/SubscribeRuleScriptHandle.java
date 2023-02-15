package com.relaxed.boot.risk.core.distributor.engine.handles;

import cn.hutool.json.JSONUtil;

import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeEnum;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeHandle;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeType;
import com.relaxed.boot.risk.model.vo.RiskRuleVO;
import com.relaxed.boot.risk.rules.script.RuleScriptHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Yakir
 * @Topic SubscribeModelHandle
 * @Description 订阅model
 * @date 2021/8/29 9:34
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SubscribeRuleScriptHandle implements SubscribeHandle {

	private final RuleScriptHandler ruleScriptHandler;

	@Override
	public SubscribeType type() {
		return SubscribeEnum.PUB_SUB_RULE_SCRIPT_CHANNEL;
	}

	@Override
	public void onMessage(String channel, String message) {
		log.info("rule script update message:{}", message);
		RiskRuleVO vo = JSONUtil.toBean(message, RiskRuleVO.class);
		// 删除规则脚本 防止内存溢出
		ruleScriptHandler.removeScript(ruleScriptHandler.buildContext(vo.getScripts(), null, null));
	}

}
