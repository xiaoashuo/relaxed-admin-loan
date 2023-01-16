package com.relaxed.boot.loan.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 银行卡类型枚举
 * @author Yakir
 */
public enum TradeStatusEnum {

	CREATE(0, "交易创建", "交易创建"), WAIT_PAY(1, "等待付款", "放款中"), SUCCESS_PAY(2, "支付成功", "放款成功"), FAILED_PAY(4, "支付失败",
			"放款失败");

	private Integer code;

	private String desc;

	private String loanDesc;

	TradeStatusEnum(Integer code, String desc, String loanDesc) {
		this.code = code;
		this.desc = desc;
		this.loanDesc = loanDesc;
	}

	private static Map<Integer, TradeStatusEnum> stateMap = new HashMap<>();

	static {
		for (TradeStatusEnum employeeActiveState : values()) {
			stateMap.put(employeeActiveState.getCode(), employeeActiveState);
		}
	}

	public static TradeStatusEnum instanceFromValue(Integer value) {
		return stateMap.get(value);
	}

	/**
	 * 获取枚举code
	 * @return 枚举code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 获取枚举注释
	 * @return 枚举注释
	 */
	public String getDesc() {
		return desc;
	}

	public String getLoanDesc() {
		return loanDesc;
	}

}
