package com.relaxed.boot.risk.rules.script.groovy;

import cn.hutool.crypto.digest.MD5;
import com.relaxed.boot.risk.rules.script.RuleScriptHandler;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yakir
 * @Topic GroovyScriptUtil
 * @Description
 * @date 2021/8/30 11:55
 * @Version 1.0
 */
public class GroovyScriptHandler implements RuleScriptHandler<GroovyParam, GroovyResult> {

	public static Map<String, GroovyObject> passedClassMap = new HashMap<>();

	public static GroovyClassLoader groovyClassLoader;

	// 初始化GroovyClassLoader
	static {
		// TODO: 检查是否有更加合适的办法获取classLoader。目前这个方法绑定在Spring上。
		ClassLoader parent = AutowiredAnnotationBeanPostProcessor.class.getClassLoader();
		groovyClassLoader = new GroovyClassLoader(parent);
	}

	/**
	 *
	 * 加载 groovy script.
	 * @param script string of groovy script
	 * @return {@link GroovyObject}
	 * @author feihu.wang 2016年8月2日
	 */
	public static GroovyObject loadScript(String script) {
		GroovyObject groovyObject = passedClassMap.get(MD5.create().digestHex(script));
		if (groovyObject == null) {
			Class groovyClass = groovyClassLoader.parseClass(script);
			try {
				groovyObject = (GroovyObject) groovyClass.newInstance();
				passedClassMap.put(MD5.create().digestHex(script), groovyObject);
			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return groovyObject;
	}

	@Override
	public GroovyParam buildContext(String ruleScript, String method, Object... args) {
		return new GroovyParam().setScript(ruleScript).setMethod(method).setArgs(args);
	}

	@Override
	public GroovyResult invokeMethod(GroovyParam context) {
		GroovyObject object = context.getObject();
		if (object == null) {
			String script = context.getScript();
			object = loadScript(script);
		}
		if (object == null) {
			return new GroovyResult();
		}
		String method = context.getMethod();
		Object[] args = context.getArgs();
		return new GroovyResult(object.invokeMethod(method, args));
	}

	/**
	 * 删除不在使用的脚本关联的groovy object, 不然内存有溢出风险。
	 */
	@Override
	public GroovyResult removeScript(GroovyParam context) {
		passedClassMap.remove(MD5.create().digestHex(context.getScript()));
		return new GroovyResult();
	}

}
