package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic LoanEnum
 * @Description
 * @date 2021/10/12 14:26
 * @Version 1.0
 */
public class LoanEnum {

	@RequiredArgsConstructor
	@Getter
	public static enum LoanPurpose {

		/**
		 * 放款
		 */
		LOAN(1, "放款"),
		/**
		 * 还款
		 */
		REPAYMENT(2, "还款"),;

		private final Integer val;

		private final String desc;

	}

	@RequiredArgsConstructor
	@Getter
	public static enum OverdueFlag {

		/**
		 * 逾期
		 */
		OVERDUE(1, "逾期"),
		/**
		 * 未逾期
		 */
		NOT_OVERDUE(0, "未逾期"),;

		private final Integer val;

		private final String desc;

	}

	@RequiredArgsConstructor
	@Getter
	public static enum LoanStatus {

		GIVING_MONEY(0, 20, "待放款"),
		GIVE_MONEY_FAIL(22, 22, "放款失败"),

		REPAYMENTING(1, 21, "正常"),
		CLEAR(2, 30, "结清"),




		;

		private final Integer code;

		private final Integer snifCode;

		private final String desc;

	}

}
