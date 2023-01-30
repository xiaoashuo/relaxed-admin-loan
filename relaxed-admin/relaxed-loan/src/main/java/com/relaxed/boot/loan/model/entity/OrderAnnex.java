package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@TableName("t_order_annex")
@Schema(title = "")  
public class OrderAnnex {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件主键
	 */
	@TableId
    @Schema(title = "文件主键", description = "文件主键")
	private Long fileId;
    
	/**
	 * 订单编号,关联t_order表
	 */
    @Schema(title = "订单编号,关联t_order表", description = "订单编号,关联t_order表")
	private Long orderId;
    
	/**
	 * 文件流水号
	 */
    @Schema(title = "文件流水号", description = "文件流水号")
	private String fileNo;
    
	/**
	 *  文件名
	 */
    @Schema(title = " 文件名", description = " 文件名")
	private String fileName;
    
	/**
	 * 文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）
	 */
    @Schema(title = "文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）", description = "文件类型（https://www.yuque.com/ntgnx4/hoyh19/ughdvq#g8rHZ）")
	private Integer fileType;
    
	/**
	 * 文件网址
	 */
    @Schema(title = "文件网址", description = "文件网址")
	private String fileUrl;
    
	/**
	 * 备注
	 */
    @Schema(title = "备注", description = "备注")
	private String remark;
    
	/**
	 * 上传时间
	 */
    @Schema(title = "上传时间", description = "上传时间")
	private LocalDateTime uploadTime;
    
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
