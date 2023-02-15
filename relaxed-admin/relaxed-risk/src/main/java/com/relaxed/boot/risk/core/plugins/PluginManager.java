package com.relaxed.boot.risk.core.plugins;

import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yakir
 * @Topic PluginManage
 * @Description
 * @date 2021/8/29 14:23
 * @Version 1.0
 */
public class PluginManager {

	public static Map<String, PluginService> pluginServiceMap() {
		return SingletonHolder.pluginServiceMap;
	}

	/**
	 * 根据插件名称获取插件
	 * @author yakir
	 * @date 2021/8/29 14:36
	 * @param pluginName
	 * @return com.relaxed.common.risk.engine.core.plugins.PluginService
	 */
	public static PluginService of(String pluginName) {
		return pluginServiceMap().get(pluginName);
	}

	private static class SingletonHolder {

		private static final Map<String, PluginService> pluginServiceMap = SpringFactoriesLoader
				.loadFactories(PluginService.class, null).stream()
				.sorted(Comparator.comparing(e -> e.pluginMeta().getOrder()))
				.collect(Collectors.toMap(PluginService::pluginName, e -> e, (oldValue, newValue) -> newValue));

	}

}
