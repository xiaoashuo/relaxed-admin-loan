package com.relaxed.boot.web.system.domain;

import lombok.Data;

/**
 * @author Yakir
 * @Topic FileUploadReq
 * @Description
 * @date 2023/2/1 13:50
 * @Version 1.0
 */
@Data
public class FileUploadReq {
    /**
     * 相对路径
     */
    private String relatedPath;
    /**
     * 是否切分日期目录
     */
    private boolean splitDate;
}
