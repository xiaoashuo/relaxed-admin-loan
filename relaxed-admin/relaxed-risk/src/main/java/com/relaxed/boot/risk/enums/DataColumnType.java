package com.relaxed.boot.risk.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic DataColumnType
 * @Description
 * @date 2021/9/26 13:58
 * @Version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum DataColumnType {

	/**
	 * 基础字段
	 */
	FIELDS("fields", "基础字段"),
	/**
	 * 预处理字段
	 */
	PREITEMS("preItems", "预处理字段"),
	/**
	 * 抽象特征字段
	 */
	ABSTRACTIONS("abstractions", "抽象字段"),
	/**
	 * 策略字段
	 */
	ACTIVATIONS("activations", "策略字段"),
	/**
	 * 规则字段
	 */
	RULES("rules", "规则字段");

	private final String name;

	private final String desc;

}
