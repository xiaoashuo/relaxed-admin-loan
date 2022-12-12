package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统角色
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@TableName("sys_role")
@Schema(title = "系统角色")
public class SysRole {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	@Schema(title = "主键ID", description = "主键ID")
	private Integer id;

	/**
	 * 角色名称
	 */
	@Schema(title = "角色名称", description = "角色名称")
	private String name;

	/**
	 * 角色标识
	 */
	@Schema(title = "角色标识", description = "角色标识")
	private String code;

	/**
	 * 角色类型，1：系统角色 2：业务角色
	 */
	@Schema(title = "角色类型，1：系统角色 2：业务角色", description = "角色类型，1：系统角色 2：业务角色")
	private Integer type;

	/**
	 * 数据范围类型
	 */
	@Schema(title = "数据范围类型", description = "数据范围类型")
	private Integer scopeType;

	/**
	 * 数据范围资源，当数据范围类型为自定义时使用
	 */
	@Schema(title = "数据范围资源，当数据范围类型为自定义时使用", description = "数据范围资源，当数据范围类型为自定义时使用")
	private String scopeResources;

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
