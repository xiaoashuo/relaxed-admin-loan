package com.relaxed.boot.loan.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Yakir
 * @Topic ProjectTemplateDTO
 * @Description
 * @date 2023/1/2 18:50
 * @Version 1.0
 */
@Data
public class ProjectTemplateDTO {
    @Schema(title = "主键", description = "主键")
    private Integer projectTemplateId;
    @Schema(title = "项目id", description = "项目id")
    private Integer projectId;
    @Schema(title = "项目名称", description = "项目名称")
    private String projectName;
    /**
     * 模板id
     */
    @Schema(title = "模板id", description = "模板id")
    private Integer templateId;
    @Schema(title = "模板名称", description = "模板名称")
    private String templateName;
    @Schema(title = "模板描述", description = "模板描述")
    private String templateDesc;
    /**
     * 文件类型
     */
    @Schema(title = "文件类型", description = "文件类型")
    private String fileType;
    @Schema(title = "文件名称", description = "文件名称")
    private String fileName;
    /**
     * 签章id
     */
    @Schema(title = "签章id", description = "签章id")
    private Integer sealId;

    /**
     * 证书id
     */
    @Schema(title = "证书id", description = "证书id")
    private Integer keystoreId;

    /**
     * 签章类型 1模板2关键字
     */
    @Schema(title = "签章类型 1模板2关键字", description = "签章类型 1模板2关键字")
    private Integer sealWay;

    /**
     * 签章坐标[{pageNum:1,locations:[{left:0,top:0,width:100,height:100}}]]
     */
    @Schema(title = "签章坐标[{pageNum:1,locations:[{left:0,top:0,width:100,height:100}}]]", description = "签章坐标[{pageNum:1,locations:[{left:0,top:0,width:100,height:100}}]]")
    private String sealLocation;

    /**
     * 关键字
     */
    @Schema(title = "关键字", description = "关键字")
    private String sealKeyword;

}
