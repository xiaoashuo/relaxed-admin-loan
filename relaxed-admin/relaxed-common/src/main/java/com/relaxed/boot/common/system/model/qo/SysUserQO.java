package com.relaxed.boot.common.system.model.qo;

import com.relaxed.boot.common.system.model.entity.BaseEntity;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 系统用户 查询对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "系统用户")
public class SysUserQO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Schema(title = "主键id", description = "主键id")
	private Integer userId;

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

	/**
	 * 电话
	 */
	@Schema(title = "电话", description = "电话")
	private String phone;

	/**
	 * 状态(1-正常,0-冻结)
	 */
	@Schema(title = "状态(1-正常,0-冻结)", description = "状态(1-正常,0-冻结)")
	private Integer status;

	/**
	 * 所属组织ID
	 */
	@Schema(title = "所属组织ID", description = "所属组织ID")
	private Integer organizationId;

	@Schema(description = "开始时间")
	private String startTime;

	@Schema(description = "结束时间")
	private String endTime;

}
