package com.relaxed.boot.web.system.domain;

import lombok.Data;

/**
 * @author Yakir
 * @Topic FileDeleteReq
 * @Description
 * @date 2022/12/14 20:12
 * @Version 1.0
 */
@Data
public class FileDeleteReq {
    private String fileId;
    private String url;
}
