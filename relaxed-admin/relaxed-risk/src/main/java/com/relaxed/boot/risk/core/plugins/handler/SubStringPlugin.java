package com.relaxed.boot.risk.core.plugins.handler;

import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.risk.core.plugins.PluginEnum;
import com.relaxed.boot.risk.core.plugins.PluginMeta;
import com.relaxed.boot.risk.core.plugins.PluginService;
import com.relaxed.boot.risk.model.vo.RiskPreItemVO;

import java.util.Map;

/**
 * @author Yakir
 * @Topic SubStringPlugin
 * @Description
 * @date 2021/8/29 14:29
 * @Version 1.0
 */
public class SubStringPlugin implements PluginService {

	@Override
	public PluginMeta pluginMeta() {
		return PluginEnum.SUB_STRING_PLUGIN;
	}

	@Override
	public String pluginName() {
		return "SUBSTRING";
	}

	@Override
	public Object handle(RiskPreItemVO preItemVO, Map<String, Object> jsonInfo, String[] sourceFields) {
		String sourceField = sourceFields[0];

		String[] args = preItemVO.getArgs().split(",");

		Object value = jsonInfo.get(sourceField);
		if (value == null) {
			return "";
		}
		String field = value.toString();
		int start = Integer.parseInt(args[0]);
		int end = Integer.parseInt(args[1]);
		return StrUtil.sub(field, start, end);
	}

}
