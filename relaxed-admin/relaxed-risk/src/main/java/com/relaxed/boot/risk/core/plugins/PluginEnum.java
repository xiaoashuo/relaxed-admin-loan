package com.relaxed.boot.risk.core.plugins;

import com.relaxed.boot.risk.core.plugins.handler.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic PluginEnum
 * @Description
 * @date 2021/8/31 18:29
 * @Version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum PluginEnum implements PluginMeta {

	/**
	 * IP转换成地址
	 */
	IP_LOCATION_PLUGIN(1, "IP转换成地址", "", Location.class),

	/**
	 * GPS转换成地址
	 */
	GPS_LOCATION_PLUGIN(2, "GPS转换成地址", "", Location.class),
	/**
	 * 字段合并
	 */
	FIELD_MERGE_PLUGIN(3, "字段合并", "", String.class),
	/**
	 * 字符串截取
	 */
	SUB_STRING_PLUGIN(4, "字符串截取", "", String.class),
	/**
	 * 手机号码归属地
	 */
	MOBILE_LOCATION_PLUGIN(5, "手机号码归属地",
			"[{\"column\":\"country\", \"title\":\"国家\", \"type\":\"STRING\"},{\"column\":\"province\", \"title\":\"省份\", \"type\":\"STRING\"},{\"column\":\"city\", \"title\":\"城市\", \"type\":\"STRING\"}]",
			Location.class),
	/**
	 * 敏感时间段(小时)
	 */
	SENSITIVE_TIME_PLUGIN(6, "敏感时间段(小时)", "", String.class),
	/**
	 * 日期格式化插件
	 */
	DATE_FORMAT_PLUGIN(7, "日期格式化插件", "", String.class),
	/**
	 * http util
	 */
	HTTP_UTIL_PLUGIN(8, "Http请求插件", "", String.class),

	;

	private final Integer order;

	private final String desc;

	private final String meta;

	private final Class<?> type;

}
