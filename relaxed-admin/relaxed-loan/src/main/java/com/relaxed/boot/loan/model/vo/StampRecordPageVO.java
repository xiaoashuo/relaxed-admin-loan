package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 签章记录分页视图对象
 *
 * @author yakir 2023-01-29 09:31:45
 */
@Data
@Schema(title = "签章记录分页视图对象")  
public class StampRecordPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 签章记录id
     */
    @Schema(title = "签章记录id", description = "签章记录id")
    private Integer stampRecordId;
    
    /**
     * 合作方编号
     */
    @Schema(title = "合作方编号", description = "合作方编号")
    private Integer projectId;
    
    /**
     * 产品编号
     */
    @Schema(title = "产品编号", description = "产品编号")
    private Integer productCode;
    
    /**
     * 信托计划编号
     */
    @Schema(title = "信托计划编号", description = "信托计划编号")
    private Integer trustPlanCode;
    
    /**
     * 盖章文件类型
     */
    @Schema(title = "盖章文件类型", description = "盖章文件类型")
    private Integer fileType;
    
    /**
     * 合同名称
     */
    @Schema(title = "合同名称", description = "合同名称")
    private String contractName;
    
    /**
     * 状态0待开始1处理中2成功3异常
     */
    @Schema(title = "状态0待开始1处理中2成功3异常", description = "状态0待开始1处理中2成功3异常")
    private Integer status;
    
    /**
     * 合作的业务id,尽可能要求合作方唯一
     */
    @Schema(title = "合作的业务id,尽可能要求合作方唯一", description = "合作的业务id,尽可能要求合作方唯一")
    private String partnerBusinessId;
    
    /**
     * 签署方式  1.模板2.关键字
     */
    @Schema(title = "签署方式  1.模板2.关键字", description = "签署方式  1.模板2.关键字")
    private Integer signWay;
    
    /**
     * 失败原因
     */
    @Schema(title = "失败原因", description = "失败原因")
    private String remark;
    
    /**
     * 扩展字段
     */
    @Schema(title = "扩展字段", description = "扩展字段")
    private String extJson;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Integer valid;
    
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
