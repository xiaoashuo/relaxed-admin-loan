package com.relaxed.boot.risk.rules;

import com.relaxed.boot.risk.rules.script.RuleScriptHandler;
import com.relaxed.boot.risk.rules.script.ScriptResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Yakir
 * @Topic AbstractRiskEvaluate
 * @Description
 * @date 2021/8/29 17:15
 * @Version 1.0
 */
@Slf4j
public abstract class AbstractRiskEvaluate implements RiskEvaluate {

	@Autowired
	protected RuleScriptHandler ruleScriptHandler;

	/**
	 * 检查特征脚本 无限制参数 增加评估步骤可以指定自己的扩充参数
	 * @author yakir
	 * @date 2021/9/8 17:02
	 * @param ruleScript
	 * @param ruleScriptEntry
	 * @param args
	 * @return boolean
	 */
	protected boolean checkScript(String ruleScript, String ruleScriptEntry, Object... args) {
		boolean ret = false;
		try {
			ScriptResult scriptResult = ruleScriptHandler
					.invokeMethod(ruleScriptHandler.buildContext(ruleScript, ruleScriptEntry, args));
			ret = scriptResult.getRunResult();
		}
		catch (Exception e) {
			log.error("params:{},rule:{}", args, ruleScript, e);
		}
		return ret;
	}

}
