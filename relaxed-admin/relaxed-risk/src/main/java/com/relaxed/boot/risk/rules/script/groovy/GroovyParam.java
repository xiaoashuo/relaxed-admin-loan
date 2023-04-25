package com.relaxed.boot.risk.rules.script.groovy;

import com.relaxed.boot.risk.rules.script.ScriptContext;
import groovy.lang.GroovyObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yakir
 * @Topic GroovyParam
 * @Description
 * @date 2021/8/30 12:34
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class GroovyParam implements ScriptContext {

	/**
	 * groovy 对象
	 */
	private GroovyObject object;

	/**
	 * groovy脚本
	 */
	private String script;

	/**
	 * 方法
	 */
	private String method;

	/**
	 * 参数
	 */
	private Object[] args;

}
