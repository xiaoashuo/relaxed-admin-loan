package com.relaxed.boot.loan.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic FillerTargetEnum
 * @Description ： 1- 应收 2- 实收 目前都是写死2实收
 * @date 2021/10/13 11:16
 * @Version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum FillerTargetEnum {

	/**
	 * 应收
	 */
	RECEIVABLE(1, "应收"),

	/**
	 * 实收
	 */
	RECEIPTS(2, "实收"),;

	private final Integer val;

	private final String desc;

}
