package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 组织架构
 *
 * @author Yakir 2022-11-23 17:56:28
 */
@Data
@TableName("sys_organization")
@Schema(title = "组织架构")
public class SysOrganization {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	@Schema(title = "ID", description = "ID")
	private Integer id;

	/**
	 * 组织名称
	 */
	@Schema(title = "组织名称", description = "组织名称")
	private String name;

	/**
	 * 父级ID
	 */
	@Schema(title = "父级ID", description = "父级ID")
	private Integer parentId;

	/**
	 * 层级信息，从根节点到当前节点的最短路径，使用-分割节点ID
	 */
	@Schema(title = "层级信息，从根节点到当前节点的最短路径，使用-分割节点ID", description = "层级信息，从根节点到当前节点的最短路径，使用-分割节点ID")
	private String hierarchy;

	/**
	 * 当前节点深度
	 */
	@Schema(title = "当前节点深度", description = "当前节点深度")
	private Integer depth;

	/**
	 * 排序字段，由小到大
	 */
	@Schema(title = "排序字段，由小到大", description = "排序字段，由小到大")
	private Integer sort;

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
	 * 创建者
	 */
	@Schema(title = "创建者", description = "创建者")
	private Integer createBy;

	/**
	 * 修改者
	 */
	@Schema(title = "修改者", description = "修改者")
	private Integer updateBy;

	/**
	 * 创建时间
	 */
	@Schema(title = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 更新时间
	 */
	@Schema(title = "更新时间")
	private LocalDateTime updatedTime;

}
