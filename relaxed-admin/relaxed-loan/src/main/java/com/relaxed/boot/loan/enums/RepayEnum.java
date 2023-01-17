package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic RepayEnum
 * @Description
 * @date 2021/10/13 10:56
 * @Version 1.0
 */
public class RepayEnum {

	/**
	 * 还款方式(各项目不同)： 1客户还款 2-机构垫付（单期代偿） 3-机构回购（整笔借据全部回购）
	 */
	@RequiredArgsConstructor
	@Getter
	public static enum RepayWay {

		/**
		 * 客户还款
		 */
		CUSTOMER_REPAYMENT(1, "客户还款"),

		;

		private final Integer val;

		private final String desc;

	}

}
