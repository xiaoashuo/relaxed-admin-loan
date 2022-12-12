package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 系统用户分页视图对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "系统用户分页视图对象")
public class SysUserPageVO {

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
	 * 密码
	 */
	@Schema(title = "密码", description = "密码")
	private String password;

	/**
	 * md5密码盐
	 */
	@Schema(title = "md5密码盐", description = "md5密码盐")
	private String salt;

	/**
	 * 头像
	 */
	@Schema(title = "头像", description = "头像")
	private String avatar;

	/**
	 * 性别(0-默认未知,1-男,2-女)
	 */
	@Schema(title = "性别(0-默认未知,1-男,2-女)", description = "性别(0-默认未知,1-男,2-女)")
	private Integer sex;

	/**
	 * 电子邮件
	 */
	@Schema(title = "电子邮件", description = "电子邮件")
	private String email;

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
	 * 账户类型
	 */
	@Schema(title = "账户类型", description = "账户类型")
	private Integer type;

	/**
	 * 所属组织ID
	 */
	@Schema(title = "所属组织ID", description = "所属组织ID")
	private Integer organizationId;

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
