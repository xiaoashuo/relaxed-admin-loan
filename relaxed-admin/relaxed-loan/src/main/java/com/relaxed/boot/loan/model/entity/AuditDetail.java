package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 审核拒绝原因表
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@TableName("t_audit_detail")
@Schema(title = "审核拒绝原因表")
public class AuditDetail {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@Schema(title = "主键", description = "主键")
	private Long auditDetailId;

	/**
	 * 订单编号，关联t_order表
	 */
	@Schema(title = "订单编号，关联t_order表", description = "订单编号，关联t_order表")
	private Long orderId;

	/**
	 * 审核编号，关联t_audit表
	 */
	@Schema(title = "审核编号，关联t_audit表", description = "审核编号，关联t_audit表")
	private Long auditId;

	/**
	 * 拒绝原因码仅拒绝时返回，可多个以“,”分隔
	 */
	@Schema(title = "拒绝原因码仅拒绝时返回，可多个以“,”分隔", description = "拒绝原因码仅拒绝时返回，可多个以“,”分隔")
	private String reasonCode;

	/**
	 * 拒绝原因描述 仅拒绝时返回
	 */
	@Schema(title = "拒绝原因描述 仅拒绝时返回", description = "拒绝原因描述 仅拒绝时返回")
	private String reasonDesc;

	/**
	 * 0 ： 系统审核。1 人工审核
	 */
	@Schema(title = "0 ： 系统审核。1 人工审核", description = "0 ： 系统审核。1 人工审核")
	private Integer auditType;

	/**
	 * 审批标记 Y:通过 N:拒绝 P:审核中 E:系统内部异常
	 */
	@Schema(title = "审批标记")
	private String auditStatus;

	/**
	 * 响应时间
	 */
	@Schema(title = "响应时间", description = "响应时间")
	private LocalDateTime resTime;

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
