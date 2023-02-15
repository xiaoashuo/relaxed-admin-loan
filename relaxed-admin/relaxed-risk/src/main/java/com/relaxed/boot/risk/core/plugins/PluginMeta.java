package com.relaxed.boot.risk.core.plugins;

/**
 * @author Yakir
 * @Topic PluginMeta
 * @Description
 * @date 2021/8/31 18:29
 * @Version 1.0
 */
public interface PluginMeta {

	/**
	 * 插件顺序
	 * @author yakir
	 * @date 2021/8/29 14:28
	 * @return java.lang.Integer
	 */
	Integer getOrder();

	/**
	 * 插件描述
	 * @author yakir
	 * @date 2021/8/29 14:26
	 * @return java.lang.String
	 */
	String getDesc();

	/**
	 * 结果类型
	 * @author yakir
	 * @date 2021/8/31 17:50
	 * @return java.lang.Class<?>
	 */
	Class<?> getType();

	/**
	 * 元数据
	 * @author yakir
	 * @date 2021/8/31 18:28
	 * @return java.lang.String
	 */
	String getMeta();

}
