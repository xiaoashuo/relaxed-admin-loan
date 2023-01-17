package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic BillEnum
 * @Description
 * @date 2021/10/13 10:14
 * @Version 1.0
 */
public class BillEnum {

	/**
	 * 本期状态 1:正常 2:逾期 5:已结清
	 */
	@RequiredArgsConstructor
	@Getter
	public static enum BillTermStatus {

		/**
		 * 正常
		 */
		NORMAL(1, "正常"),
		/**
		 * 逾期
		 */
		OVERDUE(2, "逾期"),
		/**
		 * 已结清
		 */
		CLEAR(5, "已结清"),;

		private final Integer val;

		private final String desc;

	}

}
