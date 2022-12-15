package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 签章图片表分页视图对象
 *
 * @author yakir 2022-12-15 10:25:25
 */
@Data
@Schema(title = "签章图片表分页视图对象")  
public class SealPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 签章图片主键id
     */
    @Schema(title = "签章图片主键id", description = "签章图片主键id")
    private Integer sealId;
    
    /**
     * 图片编号
     */
    @Schema(title = "图片编号", description = "图片编号")
    private String sealCode;
    
    /**
     * 个人及个人名称,公司及名称
     */
    @Schema(title = "个人及个人名称,公司及名称", description = "个人及个人名称,公司及名称")
    private String sealSubject;
    
    /**
     * 图片描述
     */
    @Schema(title = "图片描述", description = "图片描述")
    private String sealRemark;
    
    /**
     * 图片地址
     */
    @Schema(title = "图片地址", description = "图片地址")
    private String sealAddress;
    
    /**
     * 创建人
     */
    @Schema(title = "创建人", description = "创建人")
    private Integer createBy;
    
    /**
     * 修改人
     */
    @Schema(title = "修改人", description = "修改人")
    private Integer updateBy;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Long deleted;
    
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