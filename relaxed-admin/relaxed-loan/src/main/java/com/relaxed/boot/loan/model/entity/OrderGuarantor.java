package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 担保机构
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@TableName("t_order_guarantor")
@Schema(title = "担保机构")  
public class OrderGuarantor {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联人主键
	 */
	@TableId
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
	@TableField(fill = FieldFill.INSERT)
    @Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;
    
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
    @Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;
    

}
