package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 担保机构分页视图对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "担保机构分页视图对象")  
public class OrderGuarantorPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 关联人主键
     */
    @Schema(title = "关联人主键", description = "关联人主键")
    private Long guarantorId;
    
    /**
     * 订单号
     */
    @Schema(title = "订单号", description = "订单号")
    private Long orderId;
    
    /**
     * 担保机构/担保人名称
     */
    @Schema(title = "担保机构/担保人名称", description = "担保机构/担保人名称")
    private String guarantorName;
    
    /**
     * 担保函编号
     */
    @Schema(title = "担保函编号", description = "担保函编号")
    private String guarantorNo;
    
    /**
     * 备注
     */
    @Schema(title = "备注", description = "备注")
    private String remark;
    
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