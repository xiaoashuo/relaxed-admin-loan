package com.relaxed.boot.risk.rules.script;

/**
 * @author Yakir
 * @Topic ScriptResult
 * @Description
 * @date 2021/8/30 12:51
 * @Version 1.0
 */
public interface ScriptResult {

	/**
	 * 获取执行结果
	 * @author yakir
	 * @date 2021/8/30 12:52
	 * @return T
	 */
	<T> T getRunResult();

}
