package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户角色表分页视图对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "用户角色表分页视图对象")
public class SysUserRolePageVO {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Long id;

	/**
	 * 用户ID
	 */
	@Schema(title = "用户ID", description = "用户ID")
	private Integer userId;

	/**
	 * role code
	 */
	@Schema(title = "role code", description = "role code")
	private String roleCode;

}
