package com.relaxed.boot.risk.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Yakir
 * @Topic RiskEnum
 * @Description
 * @date 2021/8/31 14:26
 * @Version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum RiskEnum {

	/**
	 * 通过
	 */
	PASS,
	/**
	 * 拒绝
	 */
	REJECT,
	/**
	 * 人工
	 */
	REVIEW,;

	/**
	 * 计算 分数 大于 high 拒绝 mid-high 人工审核 low-middle 通过 否则 人工审核
	 * @author yakir
	 * @date 2021/8/31 14:30
	 * @param val
	 * @param high
	 * @param middle
	 * @param low
	 * @return com.relaxed.common.risk.engine.enums.RiskEnum
	 */
	public static RiskEnum compute(BigDecimal val, BigDecimal high, BigDecimal middle, BigDecimal low) {
		if (val.compareTo(high) >= 0) {
			return RiskEnum.REJECT;
		}
		else if (val.compareTo(middle) >= 0) {
			return RiskEnum.REVIEW;
		}
		else if (val.compareTo(low) >= 0) {
			return PASS;
		}
		return REVIEW;
	}

}
