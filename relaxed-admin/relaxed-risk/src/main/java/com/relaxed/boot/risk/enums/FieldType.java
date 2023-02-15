package com.relaxed.boot.risk.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic FieldType
 * @Description 字段类型
 * @date 2021/8/30 14:20
 * @Version 1.0
 */
@RequiredArgsConstructor
@Getter
public enum FieldType {

	/**
	 * 字符串
	 */
	STRING(1, "字符串", String.class),
	/**
	 * 整数
	 */
	INTEGER(2, "整数", Integer.class),
	/**
	 * 长整数
	 */
	LONG(3, "长整数", Long.class),
	/**
	 * 浮点数
	 */
	DOUBLE(4, "浮点数", Double.class),

	;

	private final Integer key;

	private final String desc;

	private final Class<?> clazz;

}
