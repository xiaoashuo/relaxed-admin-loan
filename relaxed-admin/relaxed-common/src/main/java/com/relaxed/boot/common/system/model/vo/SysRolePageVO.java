package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统角色分页视图对象
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@Schema(title = "系统角色分页视图对象")
public class SysRolePageVO {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Integer id;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String name;

	/**
	 *
	 */
	@Schema(title = "", description = "")
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
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 修改时间
	 */
	@Schema(title = "修改时间", description = "修改时间")
	private LocalDateTime updatedTime;

}
