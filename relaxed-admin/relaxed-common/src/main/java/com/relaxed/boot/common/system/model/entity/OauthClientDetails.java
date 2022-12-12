package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 接入客户端信息
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@TableName("oauth_client_details")
@Schema(title = "接入客户端信息")
public class OauthClientDetails {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端标识
	 */
	@TableId
	@Schema(title = "客户端标识", description = "客户端标识")
	private String clientId;

	/**
	 * 接入资源列表
	 */
	@Schema(title = "接入资源列表", description = "接入资源列表")
	private String resourceIds;

	/**
	 * 客户端秘钥
	 */
	@Schema(title = "客户端秘钥", description = "客户端秘钥")
	private String clientSecret;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String scope;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String authorizedGrantTypes;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String webServerRedirectUri;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String authorities;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Integer accessTokenValidity;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private Integer refreshTokenValidity;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String additionalInformation;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String autoapprove;

}
