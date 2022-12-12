package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 角色菜单表分页视图对象
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@Schema(title = "角色菜单表分页视图对象")
public class SysRoleMenuPageVO {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Long id;

	/**
	 * role code
	 */
	@Schema(title = "role code", description = "role code")
	private String roleCode;

	/**
	 * 菜单ID
	 */
	@Schema(title = "菜单ID", description = "菜单ID")
	private Integer menuId;

}
