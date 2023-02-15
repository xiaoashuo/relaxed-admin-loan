package com.relaxed.boot.risk.core.plugins.handler;



import com.relaxed.boot.risk.core.plugins.PluginEnum;
import com.relaxed.boot.risk.core.plugins.PluginMeta;
import com.relaxed.boot.risk.core.plugins.PluginService;
import com.relaxed.boot.risk.model.vo.RiskPreItemVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Yakir
 * @Topic DateFormatPlugin
 * @Description
 * @date 2021/8/29 14:29
 * @Version 1.0
 */
public class DateFormatPlugin implements PluginService {

	@Override
	public String pluginName() {
		return "DATE_FORMAT";
	}

	@Override
	public PluginMeta pluginMeta() {
		return PluginEnum.DATE_FORMAT_PLUGIN;
	}

	@Override
	public Object handle(RiskPreItemVO preItemVO, Map<String, Object> jsonInfo, String[] sourceFields) {
		String formatStr = preItemVO.getArgs();
		String sourceField = sourceFields[0];
		String value = jsonInfo.get(sourceField).toString();
		return LocalDateTime.parse(value).format(DateTimeFormatter.ofPattern(formatStr));
	}

}
