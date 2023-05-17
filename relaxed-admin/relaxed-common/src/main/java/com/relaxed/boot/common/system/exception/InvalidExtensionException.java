package com.relaxed.boot.common.system.exception;

/**
 * @author Yakir
 * @Topic InvalidExtensionException
 * @Description
 * @date 2023/5/17 10:46
 * @Version 1.0
 */
public class InvalidExtensionException extends FileException{
    public InvalidExtensionException(int code, String message) {
        super(code, message);
    }

    public InvalidExtensionException(int code, Object... args) {
        super(code, "invalid.extension.type {}", args);
    }
}
