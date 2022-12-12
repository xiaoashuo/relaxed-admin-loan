package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 字典表
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@TableName("sys_dict")
@Schema(title = "字典表")
public class SysDict {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	@Schema(title = "编号", description = "编号")
	private Integer id;

	/**
	 * 标识
	 */
	@Schema(title = "标识", description = "标识")
	private String code;

	/**
	 * 名称
	 */
	@Schema(title = "名称", description = "名称")
	private String title;

	/**
	 * 值类型,1:Number 2:String 3:Boolean
	 */
	@Schema(title = "值类型,1:Number 2:String 3:Boolean", description = "值类型,1:Number 2:String 3:Boolean")
	private Integer valueType;

	/**
	 * hash值，当字典项被修改时变更
	 */
	@Schema(title = "hash值，当字典项被修改时变更", description = "hash值，当字典项被修改时变更")
	private String hashCode;

	/**
	 * 备注
	 */
	@Schema(title = "备注", description = "备注")
	private String remarks;

	/**
	 * 逻辑删除标识，未删除为 0，已删除为删除时间
	 */
	@Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
	private Long deleted;

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
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(title = "修改时间", description = "修改时间")
	private LocalDateTime updatedTime;

}
