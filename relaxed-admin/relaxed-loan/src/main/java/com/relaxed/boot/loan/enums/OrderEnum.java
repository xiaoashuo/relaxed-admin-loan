package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * DIN
 *
 * @author Yakir
 */

@Getter
public class OrderEnum {

	@RequiredArgsConstructor
	@Getter
	public enum OrderStatus {

		// 订单阶段（）
		WAIT_IN(0, "待处理"),

		CREDITING(10, "审批中"),

		CREDIT_PASS(11, "审批通过"),

		CREDIT_DENY(12, "审批拒绝"),

		CREDIT_SUCCESS(14, "已用信"),

		ORDER_INVALID(16, "失效"),

		;

		private final Integer val;

		private final String desc;

		public static String getDesc(Integer code) throws Exception {
			for (OrderStatus e : OrderStatus.values()) {
				if (e.getVal().equals(code)) {
					return e.getDesc();
				}
			}
			return "未知";
		}

	}

}
