package com.relaxed.boot.common.system.exception;

import com.relaxed.common.core.exception.BusinessException;

/**
 * @author Yakir
 * @Topic FileException
 * @Description
 * @date 2022/11/27 12:19
 * @Version 1.0
 */
public class FileException extends BusinessException {

	public FileException(int code, String message) {
		super(code, message);
	}

	public FileException(int code, String messageTemplate, Object... args) {
		super(code, messageTemplate, args);
	}

}
