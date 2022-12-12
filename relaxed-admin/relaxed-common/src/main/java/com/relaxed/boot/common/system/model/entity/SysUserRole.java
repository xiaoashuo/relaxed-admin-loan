package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户角色表
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@TableName("sys_user_role")
@Schema(title = "用户角色表")
public class SysUserRole {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
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
