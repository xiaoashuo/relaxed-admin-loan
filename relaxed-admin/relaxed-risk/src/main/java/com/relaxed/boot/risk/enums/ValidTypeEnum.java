package com.relaxed.boot.risk.enums;

import cn.hutool.core.lang.Validator;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@RequiredArgsConstructor
@Getter
public enum ValidTypeEnum {

	/**
	 * 效验是否整型
	 */
	INTEGER(1, "整型", (fieldVo, value) -> {
		// 验证字段值类型是否匹配当前
		return (value instanceof Integer) ? FieldValidResult.pass()
				: FieldValidResult.reject(fieldVo.getFieldName(), "不是整数类型");
	}),
	/**
	 * 长整型
	 */
	LONG(2, "长整型", (fieldVo, value) -> {
		// 验证字段值类型是否匹配当前
		return (value instanceof Long) ? FieldValidResult.pass()
				: FieldValidResult.reject(fieldVo.getFieldName(), "不是长整数类型");
	}),
	/**
	 * 浮点型效验
	 */
	DOUBLE(3, "浮点型", (fieldVo, value) -> {
		// 验证字段值类型是否匹配当前
		return (value instanceof Double) ? FieldValidResult.pass()
				: FieldValidResult.reject(fieldVo.getFieldName(), "不是浮点型类型");
	}),
	/**
	 * 字符串效验
	 */
	STRING(4, "字符串", (fieldVo, value) -> {
		// 验证字段值类型是否匹配当前
		return (value instanceof String) ? FieldValidResult.pass()
				: FieldValidResult.reject(fieldVo.getFieldName(), "不是字符串类型");
	}),

	/**
	 * 长度效验 必须相同
	 */
	LENGTH(11, "字符串长度", (fieldVo, value) -> {

		Integer len = Integer.parseInt(fieldVo.getValidateArgs());
		boolean pass = value != null && value instanceof String && ((String) value).length() == len;
		// 验证字段值类型是否匹配当前
		return pass ? FieldValidResult.pass() : FieldValidResult.reject(fieldVo.getFieldName(), "长度不正确");
	}),
	/**
	 * 最大长度效验
	 */
	MAX_LENGTH(12, "最大长度", (fieldVo, value) -> {
		Integer len = Integer.parseInt(fieldVo.getValidateArgs());
		boolean pass = value != null && value instanceof String && ((String) value).length() <= len;
		// 验证字段值类型是否匹配当前
		return pass ? FieldValidResult.pass() : FieldValidResult.reject(fieldVo.getFieldName(), "超出最大长度");
	}),
	/**
	 * 最小长度效验
	 */
	MIN_LENGTH(13, "最小长度", (fieldVo, value) -> {
		Integer len = Integer.parseInt(fieldVo.getValidateArgs());
		boolean pass = value != null && value instanceof String && ((String) value).length() >= len;
		// 验证字段值类型是否匹配当前
		return pass ? FieldValidResult.pass() : FieldValidResult.reject(fieldVo.getFieldName(), "小于最小长度");
	}),
	/**
	 * email效验
	 */
	EMAIL(21, "邮箱", (fieldVo, value) -> {

		boolean pass = value != null && value instanceof String && Validator.isEmail(value.toString());
		// 验证字段值类型是否匹配当前
		return pass ? FieldValidResult.pass() : FieldValidResult.reject(fieldVo.getFieldName(), "邮箱格式不正确");
	}),
	/**
	 * 手机效验
	 */
	MOBILE(22, "手机", (fieldVo, value) -> {

		boolean pass = value != null && value instanceof String && Validator.isMobile(value.toString());
		// 验证字段值类型是否匹配当前
		return pass ? FieldValidResult.pass() : FieldValidResult.reject(fieldVo.getFieldName(), "手机格式不正确");
	}),

	/**
	 * ip效验
	 */
	IP(23, "IP", (fieldVo, value) -> {

		boolean pass = value != null && value instanceof String
				&& (Validator.isIpv4(value.toString()) || Validator.isIpv6(value.toString()));
		// 验证字段值类型是否匹配当前
		return pass ? FieldValidResult.pass() : FieldValidResult.reject(fieldVo.getFieldName(), "IP格式不正确");
	}),
	/**
	 * 正则效验
	 */
	REGEX(99, "正则表达式", (fieldVo, value) -> {

		boolean pass = value != null && value instanceof String && value.toString().matches(fieldVo.getValidateArgs());
		// 验证字段值类型是否匹配当前
		return pass ? FieldValidResult.pass() : FieldValidResult.reject(fieldVo.getFieldName(), "正则效验不正确");
	});

	/**
	 * 类型
	 */
	private final Integer type;

	/**
	 * 描述
	 */
	private final String desc;

	/**
	 * 验证函数c
	 */
	private final BiFunction<RiskFieldVO, Object, FieldValidResult> validFunctions;

}
