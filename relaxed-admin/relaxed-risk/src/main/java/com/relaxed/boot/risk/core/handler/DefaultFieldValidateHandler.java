package com.relaxed.boot.risk.core.handler;

import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.risk.enums.FieldValidResult;
import com.relaxed.boot.risk.enums.ValidTypeEnum;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;

import java.util.Map;

/**
 * @author Yakir
 * @Topic DefaultFieldValidateHandler
 * @Description
 * @date 2021/8/29 13:11
 * @Version 1.0
 */
public class DefaultFieldValidateHandler implements FieldValidateHandler {

	@Override
	public FieldValidResult valid(RiskFieldVO fieldVO, Map context) {

		String validateType = fieldVO.getValidateType();
		// 验证类型为空 则不进行验证 直接放行
		if (StrUtil.isEmpty(validateType)) {
			return FieldValidResult.pass();
		}
		String fieldName = fieldVO.getFieldName();
		Object value = context.get(fieldName);
		if (value == null) {
			return FieldValidResult.reject(fieldName, "字段为空");
		}
		return ValidTypeEnum.valueOf(validateType).getValidFunctions().apply(fieldVO, value);
	}

}
