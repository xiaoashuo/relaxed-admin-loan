package com.relaxed.boot.risk.core.plugins;

import com.relaxed.boot.risk.model.vo.RiskPreItemVO;

import java.util.Map;

/**
 * @author Yakir
 * @Topic PluginService
 * @Description
 * @date 2021/8/29 14:24
 * @Version 1.0
 */
public interface PluginService {

	/**
	 * 插件名称
	 * @author yakir
	 * @date 2021/8/29 14:25
	 * @return java.lang.String
	 */
	default String pluginName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * 插件元数据
	 * @author yakir
	 * @date 2021/9/1 10:37
	 * @return com.relaxed.common.risk.engine.core.plugins.PluginMeta
	 */
	PluginMeta pluginMeta();

	/**
	 * 处理字段方法
	 * @author yakir
	 * @date 2021/8/29 14:25
	 * @param preItemVO
	 * @param jsonInfo
	 * @param sourceFields
	 * @return java.lang.Object
	 */
	Object handle(RiskPreItemVO preItemVO, Map<String, Object> jsonInfo, String[] sourceFields);

}
