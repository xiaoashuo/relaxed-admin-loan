package com.relaxed.boot.risk.exception;

import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.result.ResultCode;

/**
 * @author Yakir
 * @Topic RiskException
 * @Description
 * @date 2021/9/19 17:36
 * @Version 1.0
 */
public class RiskException extends BusinessException {

	public RiskException(ResultCode resultCode) {
		super(resultCode);
	}

}
