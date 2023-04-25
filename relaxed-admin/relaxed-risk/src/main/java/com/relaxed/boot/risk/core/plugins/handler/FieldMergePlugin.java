package com.relaxed.boot.risk.core.plugins.handler;

import cn.hutool.core.util.ArrayUtil;
import com.relaxed.boot.risk.core.plugins.PluginEnum;
import com.relaxed.boot.risk.core.plugins.PluginMeta;
import com.relaxed.boot.risk.core.plugins.PluginService;
import com.relaxed.boot.risk.model.vo.RiskPreItemVO;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yakir
 * @Topic FieldMergePlugin
 * @Description 字段合并插件
 * @date 2021/8/29 14:29
 * @Version 1.0
 */
public class FieldMergePlugin implements PluginService {

	@Override
	public String pluginName() {
		return "FIELD_MERGE";
	}

	@Override
	public PluginMeta pluginMeta() {
		return PluginEnum.FIELD_MERGE_PLUGIN;
	}

	@Override
	public Object handle(RiskPreItemVO preItemVO, Map<String, Object> jsonInfo, String[] sourceFields) {
		if (ArrayUtil.isEmpty(sourceFields)) {
			return "";
		}
		return Arrays.asList(sourceFields).stream().map(f -> jsonInfo.get(f) == null ? "" : f)
				.collect(Collectors.joining("_"));
	}

}
