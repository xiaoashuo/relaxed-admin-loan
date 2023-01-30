package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 项目模板配置表分页视图对象
 *
 * @author yakir 2023-01-02 09:55:28
 */
@Data
@Schema(title = "项目模板配置表分页视图对象")  
public class ProjectTemplatePageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键", description = "主键")
    private Integer projectTemplateId;
    @Schema(title = "项目id", description = "项目id")
    private Integer projectId;
    @Schema(title = "项目名称", description = "项目名称")
    private String projectName;


    /**
     * 文件类型
     */
    @Schema(title = "文件类型", description = "文件类型")
    private Integer fileType;
    @Schema(title = "目标文件类型", description = "目标文件类型")
    private Integer destFileType;
    /**
     * 模板id
     */
    @Schema(title = "模板id", description = "模板id")
    private Integer templateId;
    @Schema(title = "模板名称", description = "模板名称")
    private String templateName;
    
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
    
    /**
     * 是否有效(0:未删除 1 或时间戳为已删除)
     */
    @Schema(title = "是否有效(0:未删除 1 或时间戳为已删除)", description = "是否有效(0:未删除 1 或时间戳为已删除)")
    private Integer deleted;
    
    /**
     * 创建时间
     */
    @Schema(title = "创建时间", description = "创建时间")
    private LocalDateTime createdTime;
    
    /**
     * 更新时间
     */
    @Schema(title = "更新时间", description = "更新时间")
    private LocalDateTime updatedTime;
    

}
