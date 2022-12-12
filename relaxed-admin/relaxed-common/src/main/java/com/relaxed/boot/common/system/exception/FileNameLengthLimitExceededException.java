package com.relaxed.boot.common.system.exception;

/**
 * @author Yakir
 * @Topic FileNameLengthLimitExceededException
 * @Description
 * @date 2022/11/27 12:20
 * @Version 1.0
 */
public class FileNameLengthLimitExceededException extends FileException{
    public FileNameLengthLimitExceededException(int code, String message) {
        super(code, message);
    }

    public FileNameLengthLimitExceededException(int code,  Object... args) {
        super(code, "upload.filename.exceed.length {}", args);
    }
}
