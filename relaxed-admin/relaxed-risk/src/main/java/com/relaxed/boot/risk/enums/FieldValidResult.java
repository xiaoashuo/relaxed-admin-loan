package com.relaxed.boot.risk.enums;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yakir
 * @Topic FieldValidResult
 * @Description
 * @date 2021/9/28 14:41
 * @Version 1.0
 */
@Accessors(chain = true)
@Data
public class FieldValidResult {

	/**
	 * 有效 true 无效false
	 */
	private boolean valid;

	/**
	 * 字段名
	 */
	private String fieldName;

	/**
	 * 错误描述
	 */
	private String errorDesc;

	public static FieldValidResult pass() {
		return new FieldValidResult().setValid(true);
	}

	public static FieldValidResult reject(String fieldName, String message) {
		return new FieldValidResult().setValid(false).setFieldName(fieldName).setErrorDesc(message);
	}

}
