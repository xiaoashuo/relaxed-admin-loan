package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 共同借款人
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@TableName("t_order_borrow")
@Schema(title = "共同借款人")  
public class OrderBorrow {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联人主键
	 */
	@TableId
    @Schema(title = "关联人主键", description = "关联人主键")
	private Long userId;
    
	/**
	 * 订单号
	 */
    @Schema(title = "订单号", description = "订单号")
	private Long orderId;
    
	/**
	 * 关联人姓名
	 */
    @Schema(title = "关联人姓名", description = "关联人姓名")
	private String relatedName;
    
	/**
	 * 联系人证件号码
	 */
    @Schema(title = "联系人证件号码", description = "联系人证件号码")
	private String certificateNo;
    
	/**
	 * 联系人电话

	 */
    @Schema(title = "联系人电话 ", description = "联系人电话 ")
	private String mobileNo;
    
	/**
	 * 地址

	 */
    @Schema(title = "地址 ", description = "地址 ")
	private String address;
    
	/**
	 * 用户电子邮箱
	 */
    @Schema(title = "用户电子邮箱", description = "用户电子邮箱")
	private String eMail;
    
	/**
	 * 是否担保人1是2否
	 */
    @Schema(title = "是否担保人1是2否", description = "是否担保人1是2否")
	private String guarantorFlag;
    
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
