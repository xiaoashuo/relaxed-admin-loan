package com.relaxed.boot.common.system.utils;

import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;

/**
 * @author Yakir
 * @Topic SysLogUtil
 * @Description
 * @date 2023/1/17 13:43
 * @Version 1.0
 */
public class LogFormatUtil {

	/**
	 * 任务[风控审核查询],开始,任务时间2022-12-10 12:10:10
	 */
	private static final String logTemplate = "任务[{}],动作:{},任务时间:{},附加信息:{}";

	public static String format(String subject, String action, LocalDateTime taskTime) {
		return StrUtil.format(logTemplate, subject, action, taskTime, "无");
	}

	public static String format(String subject, String action, LocalDateTime taskTime, String extra) {
		return StrUtil.format(logTemplate, subject, action, taskTime, extra);
	}

	public static String format(String subject, String action, LocalDateTime taskTime, String messageTemplate,
			Object... args) {
		return StrUtil.format(logTemplate, subject, action, taskTime, StrUtil.format(messageTemplate, args));
	}

}
