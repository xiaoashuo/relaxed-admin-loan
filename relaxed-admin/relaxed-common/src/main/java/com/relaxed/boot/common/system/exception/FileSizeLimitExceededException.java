package com.relaxed.boot.common.system.exception;

/**
 * @author Yakir
 * @Topic FileSizeLimitExceededException
 * @Description
 * @date 2022/11/27 12:23
 * @Version 1.0
 */
public class FileSizeLimitExceededException extends FileException {

	public FileSizeLimitExceededException(int code, String message) {
		super(code, message);
	}

	public FileSizeLimitExceededException(int code, Object... args) {
		super(code, "upload.exceed.maxSize {}", args);
	}

}
