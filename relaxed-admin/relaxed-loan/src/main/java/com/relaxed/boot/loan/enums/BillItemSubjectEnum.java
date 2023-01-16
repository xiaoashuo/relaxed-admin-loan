package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 科目主题
 * @author Yakir
 */
@RequiredArgsConstructor
@Getter
public enum BillItemSubjectEnum {

	PRINCIPAL(1, "本金"), INTEREST(11, "利息"), INTEREST_PENALTY(21, "罚息"),;

	public final Integer code;

	public final String desc;

	public static String getDescByCode(int code) {
		for (BillItemSubjectEnum e : BillItemSubjectEnum.values()) {
			if (e.code == code) {
				return e.desc;
			}
		}
		return "";
	}

}
