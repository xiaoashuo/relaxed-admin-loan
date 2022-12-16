package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 模板文件文本域表分页视图对象
 *
 * @author Yakir 2022-12-16 11:18:37
 */
@Data
@Schema(title = "模板文件文本域表分页视图对象")  
public class TemplateAreaPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 模板参数主键
     */
    @Schema(title = "模板参数主键", description = "模板参数主键")
    private Integer areaId;
    
    /**
     * 文件模板编号
     */
    @Schema(title = "文件模板编号", description = "文件模板编号")
    private Integer templateId;
    
    /**
     * 文本域
     */
    @Schema(title = "文本域", description = "文本域")
    private String textArea;
    
    /**
     * 节点名称
     */
    @Schema(title = "节点名称", description = "节点名称")
    private String tagName;
    
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