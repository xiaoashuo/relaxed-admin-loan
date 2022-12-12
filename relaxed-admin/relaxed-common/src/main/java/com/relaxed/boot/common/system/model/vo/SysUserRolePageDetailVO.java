package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yakir
 * @Topic SysUserRoleRelatedPageVO
 * @Description
 * @date 2022/11/19 15:10
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserRolePageDetailVO extends SysUserRolePageVO {

	/**
	 * 登录账号
	 */
	@Schema(title = "登录账号", description = "登录账号")
	private String username;

	/**
	 * 显示名称
	 */
	@Schema(title = "显示名称", description = "显示名称")
	private String nickname;

}
