package com.relaxed.boot.risk.rules.extractor;

import com.relaxed.boot.risk.enums.FieldType;
import com.relaxed.boot.risk.rules.EvaluateContext;
import com.relaxed.boot.risk.rules.EvaluateReport;

import java.util.Map;

/**
 * @author Yakir
 * @Topic FieldExtractor
 * @Description
 * @date 2021/8/30 13:35
 * @Version 1.0
 */
public interface FieldExtractor {

	/**
	 * 字段名提取器 eg: fields.userId -> userId
	 * @author yakir
	 * @date 2021/8/30 13:36
	 * @param originFieldName
	 * @return java.lang.String 转换后字段名
	 */
	String extractorFieldName(String originFieldName);

	/**
	 * 提取字段值
	 * @author yakir
	 * @date 2021/9/9 14:42
	 * @param fieldName
	 * @param evaluateContext
	 * @param evaluateReport
	 * @return T
	 */
	<T> T extractorFieldValue(String fieldName, EvaluateContext evaluateContext, EvaluateReport evaluateReport);

	/**
	 * 提取字段类型
	 * @author yakir
	 * @date 2021/8/30 14:24
	 * @param fieldName
	 * @param fieldType
	 * @return com.relaxed.common.risk.engine.enums.FieldType
	 */
	FieldType extractorFieldType(String fieldName, Map<String, String> fieldType);

}
