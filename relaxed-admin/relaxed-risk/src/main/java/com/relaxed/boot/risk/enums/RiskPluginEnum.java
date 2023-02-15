package com.relaxed.boot.risk.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic PluginEnum
 * @Description
 * @date 2021/8/29 14:19
 * @Version 1.0
 */
public class RiskPluginEnum {

	@RequiredArgsConstructor
	@Getter
	public static enum StatusEnum {

		/**
		 * 不启用
		 */
		NOT_ENABLE(0, "不启用"),
		/**
		 * 启用
		 */
		ENABLE(1, "启用"),;

		private final Integer status;

		private final String desc;

	}

}
