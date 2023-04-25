package com.relaxed.boot.risk.rules.script.groovy;

import com.relaxed.boot.risk.rules.script.ScriptResult;
import lombok.Data;

/**
 * @author Yakir
 * @Topic GroovyResult
 * @Description
 * @date 2021/8/30 12:36
 * @Version 1.0
 */
@Data
public class GroovyResult implements ScriptResult {

	/**
	 * 执行结果
	 */
	private Object executeResult;

	public GroovyResult() {

	}

	public GroovyResult(Object executeResult) {
		this.executeResult = executeResult;
	}

	@Override
	public <T> T getRunResult() {
		return (T) executeResult;
	}

}
