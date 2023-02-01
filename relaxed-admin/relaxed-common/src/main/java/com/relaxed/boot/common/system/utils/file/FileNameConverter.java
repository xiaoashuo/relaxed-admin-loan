package com.relaxed.boot.common.system.utils.file;

/**
 * @author Yakir
 * @Topic FileNameHandler
 * @Description
 * @date 2023/2/1 17:38
 * @Version 1.0
 */
public interface FileNameConverter {

    /**
     * 转换生成文件名称
     * @param originalFilename 原始文件名称
     * @return 转换后文件名称
     */
    String extractFileName(String originalFilename);
}
