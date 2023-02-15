package com.relaxed.boot.risk.rules.script;

/**
 * @author Yakir
 * @Topic RuleScriptHandler
 * @Description 脚本规则帮助类
 * @date 2021/8/30 11:54
 * @Version 1.0
 */
public interface RuleScriptHandler<T extends ScriptContext, R extends ScriptResult> {

	/**
	 * 构建上下文参数
	 * @author yakir
	 * @date 2021/8/30 12:49
	 * @param ruleScript
	 * @param method
	 * @param args
	 * @return T
	 */
	T buildContext(String ruleScript, String method, Object... args);

	/**
	 * 引用方法
	 * @author yakir
	 * @date 2021/8/30 12:33
	 * @param context
	 * @return R
	 */
	R invokeMethod(T context);

	/**
	 * 删除脚本
	 * @author yakir
	 * @date 2021/8/30 12:37
	 * @param context
	 * @return R
	 */
	R removeScript(T context);

}
