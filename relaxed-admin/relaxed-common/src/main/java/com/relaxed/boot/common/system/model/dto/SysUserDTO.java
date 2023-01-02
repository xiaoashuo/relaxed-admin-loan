package com.relaxed.boot.common.system.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.relaxed.common.core.validation.group.GroupType;
import com.relaxed.common.desensitize.enums.RegexDesensitizationTypeEnum;
import com.relaxed.common.desensitize.json.annotation.JsonRegexDesensitize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统用户表
 *
 * @author ballcat code generator
 * @date 2019-09-12 20:39:31
 */
@Data
@Schema(title = "系统用户DTO")
public class SysUserDTO {

	/**
	 * 主键id
	 */
	@NotNull(message = "userId不能为空", groups = GroupType.UpdateGroup.class)
	@Schema(title = "主键id")
	private Integer userId;

	// /**
	// * 前端传入密码
	// */
	// @NotEmpty(message = "pass {}", groups = GroupType.CreateGroup.class)
	//// @JsonRegexDesensitize(type = RegexDesensitizationTypeEnum.ENCRYPTED_PASSWORD)
	// @Schema(title = "前端传入密码")
	// private String pass;

	/**
	 * 用户明文密码, 不参与前后端交互
	 */
	// @NotEmpty(message = "password 不能为空", groups = GroupType.CreateGroup.class)
	@JsonRegexDesensitize(type = RegexDesensitizationTypeEnum.ENCRYPTED_PASSWORD)
	@Schema(title = "密码", description = "密码")
	private String password;

	/**
	 * 登录账号
	 */
	@NotEmpty(message = "username 不能为空")
	@Schema(title = "登录账号")
	private String username;

	/**
	 * 昵称
	 */
	@NotEmpty(message = "nickname 不能为空")
	@Schema(title = "昵称")
	private String nickname;

	/**
	 * 头像
	 */
	@Schema(title = "头像")
	private String avatar;

	/**
	 * 性别(0-默认未知,1-男,2-女)
	 */
	@Schema(title = "性别(0-默认未知,1-男,2-女)")
	private Integer sex;

	/**
	 * 电子邮件
	 */
	@Schema(title = "电子邮件")
	private String email;

	/**
	 * 电话
	 */
	@Schema(title = "电话")
	private String phone;

	/**
	 * 状态(1-正常,2-冻结)
	 */
	@Range(message = "status 不正确", min = 0, max = 1)
	@Schema(title = "状态(1-正常,0-冻结)")
	private Integer status;

	/**
	 * 组织机构ID
	 */
	@Schema(title = "组织机构ID")
	private Integer organizationId;

	/**
	 * 角色标识列表
	 */
	@Schema(title = "角色标识列表")
	private List<String> roleCodes;

}
