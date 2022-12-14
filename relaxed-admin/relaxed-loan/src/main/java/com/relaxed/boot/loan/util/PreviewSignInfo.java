package com.relaxed.boot.loan.util;

import lombok.Data;

import java.util.List;

/**
 * @author Yakir
 * @Topic PreviewSignInfo
 * @Description 预览签名信息
 * @date 2022/12/14 10:12
 * @Version 1.0
 */
@Data
public class PreviewSignInfo {
    private String imgPath;
    /**
     * 是否在内容上 添加内容 默认为false
     */
    private boolean contentBefore=false;

    private List<KeywordLocation> keywordLocationList;
}
