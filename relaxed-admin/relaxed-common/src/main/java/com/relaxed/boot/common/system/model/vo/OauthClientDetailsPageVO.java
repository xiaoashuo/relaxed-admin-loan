package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 接入客户端信息分页视图对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "接入客户端信息分页视图对象")
public class OauthClientDetailsPageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端标识
	 */
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
