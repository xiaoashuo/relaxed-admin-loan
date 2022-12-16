package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 产品配置表分页视图对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "产品配置表分页视图对象")  
public class ProjectPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键", description = "主键")
    private Integer projectId;
    
    /**
     * 信托项目主键（t_config_partner）
     */
    @Schema(title = "信托项目主键（t_config_partner）", description = "信托项目主键（t_config_partner）")
    private Integer partnerId;
    
    /**
     * 信托产品主键（t_config_product）
     */
    @Schema(title = "信托产品主键（t_config_product）", description = "信托产品主键（t_config_product）")
    private Integer productId;
    
    /**
     * 信托计划主键（t_config_trust_plan）
     */
    @Schema(title = "信托计划主键（t_config_trust_plan）", description = "信托计划主键（t_config_trust_plan）")
    private Integer trustPlanId;
    
    /**
     * 合作项目代号
     */
    @Schema(title = "合作项目代号", description = "合作项目代号")
    private String projectCode;
    
    /**
     * 合作项目名称
     */
    @Schema(title = "合作项目名称", description = "合作项目名称")
    private String projectName;
    

    
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
