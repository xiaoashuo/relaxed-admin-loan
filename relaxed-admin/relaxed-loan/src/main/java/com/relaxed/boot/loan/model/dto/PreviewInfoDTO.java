package com.relaxed.boot.loan.model.dto;

import lombok.Data;

/**
 * @author Yakir
 * @Topic PreviewInfoDTO
 * @Description
 * @date 2022/12/18 15:56
 * @Version 1.0
 */
@Data
public class PreviewInfoDTO {
    /**
     * 证书id
     */
    private Integer keystoreId;

    /**
     * 模板id
     */
    private Integer templateId;
    /**
     * 签章id
     */
    private Integer sealId;
    /**
     * 关键字
     */
    private String keyword;
    /**
     * 文件类型
     */
    private Integer fileType;
}
