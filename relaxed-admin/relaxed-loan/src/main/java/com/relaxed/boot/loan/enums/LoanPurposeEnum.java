package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic LoanPurposeEnum
 * @Description
 * @date 2021/9/16 11:41
 * @Version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum LoanPurposeEnum {

	/**
	 * 放款
	 */
	REPAYMENT(1, "放款"),
	/**
	 * 收款
	 */
	RECEIPT(2, "收款"),;

	private final Integer val;

	private final String desc;

}
