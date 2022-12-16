package com.relaxed.boot.loan.util.word;

/**
 * @author Yakir
 * @Topic WordException
 * @Description
 * @date 2022/4/24 11:44
 * @Version 1.0
 */
public class WordException extends RuntimeException {

	public WordException() {
		super();
	}

	public WordException(String message) {
		super(message);
	}

	public WordException(String message, Throwable cause) {
		super(message, cause);
	}

	public WordException(Throwable cause) {
		super(cause);
	}

	protected WordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
