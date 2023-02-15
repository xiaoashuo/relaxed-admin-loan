package com.relaxed.boot.risk.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yakir
 * @Topic ModelEnums
 * @Description
 * @date 2021/8/29 10:17
 * @Version 1.0
 */
public class RiskModelEnum {

	@RequiredArgsConstructor
	@Getter
	public static enum TemplateEnum {

		/**
		 * 模板
		 */
		TEMPLATE(1, "模板"),
		/**
		 * 非模板
		 */
		NOT_TEMPLATE(0, "非模板"),;

		private final Integer val;

		private final String desc;

		/**
		 * 模型是否为模板
		 * @author yakir
		 * @date 2021/9/12 17:20
		 * @param val
		 * @return boolean
		 */
		public static boolean isTemplate(Integer val) {
			return TEMPLATE.getVal().equals(val);
		}

	}

	@RequiredArgsConstructor
	@Getter
	public static enum StatusEnum {

		/**
		 * 初始
		 */
		INIT(0, "初始"),
		/**
		 * 启用
		 */
		ENABLE(1, "启用"),
		/**
		 * 不启用
		 */
		DIS_ENABLE(2, "不启用"),;

		private final Integer status;

		private final String desc;

	}

	@RequiredArgsConstructor
	@Getter
	public static enum FieldValidEnum {

		/**
		 * 不验证
		 */
		NO_VALID(0, "不验证"),
		/**
		 * 验证
		 */
		VALID(1, "验证"),

		;

		private final Integer val;

		private final String desc;

		/**
		 * 判断字段是否需要验证
		 * @author yakir
		 * @date 2021/8/29 12:12
		 * @param fieldValue
		 * @return boolean
		 */
		public static boolean validField(Integer fieldValue) {
			return FieldValidEnum.VALID.getVal().equals(fieldValue);
		}

	}

}
