package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic StampEnum
 * @Description
 * @date 2023/1/30 9:41
 * @Version 1.0
 */
@Getter
public class StampEnum {

	@RequiredArgsConstructor
	@Getter
	public enum Status {

		WAIT_START(0, "待开始"),

		PROCESSING(1, "处理中"),

		SUCCESS(2, "成功"), FAIL(3, "异常"),;

		private final Integer val;

		private final String desc;

	}

	@RequiredArgsConstructor
	@Getter
	public enum SealWay {

		TEMPLATE(1, "模板"),

		KEYWORD(2, "关键字"),;

		private final Integer val;

		private final String desc;

	}

}
