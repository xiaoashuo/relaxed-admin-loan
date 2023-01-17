package com.relaxed.boot.loan.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 1-正常还款 2-减免（因业务、营销原因对收款进行减免） 5-优惠券抵扣（因业务、营销原因对收款进行优惠券抵扣）
 *
 * 6-退款退货
 */
public enum FillerTypeEnum {

	A(1, "正常还款"), B(2, "减免"),

	C(5, "优惠券抵扣"), D(6, "退款退货"), E(3, "应收冲正（因错误对应收金额进行调整，减少应收传正值）"), F(4, "实收冲正（因错误对实收金额进行调整，减少实收传负值）"),

	;

	private Integer code;

	private String desc;

	FillerTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	private static Map<Integer, FillerTypeEnum> stateMap = new HashMap<>();

	static {
		for (FillerTypeEnum employeeActiveState : values()) {
			stateMap.put(employeeActiveState.getCode(), employeeActiveState);
		}
	}

	public static FillerTypeEnum instanceFromValue(Integer value) {
		return stateMap.get(value);
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
