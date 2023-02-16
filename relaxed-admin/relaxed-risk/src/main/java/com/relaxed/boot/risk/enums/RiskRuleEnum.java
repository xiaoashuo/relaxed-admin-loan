package com.relaxed.boot.risk.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic PreItemEnum
 * @Description
 * @date 2021/8/29 14:19
 * @Version 1.0
 */
public class RiskRuleEnum {

	@RequiredArgsConstructor
	@Getter
	public static enum StatusEnum {

		/**
		 * 初始
		 */
		INIT(0, "初始"),
		/**
		 * 启用
		 */
		ENABLE(1, "启用"),
		/**
		 * 不启用
		 */
		DIS_ENABLE(2, "不启用"),;

		private final Integer status;

		private final String desc;

	}

}
