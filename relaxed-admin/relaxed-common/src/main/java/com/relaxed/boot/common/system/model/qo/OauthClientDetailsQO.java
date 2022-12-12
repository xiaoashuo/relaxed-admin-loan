package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 接入客户端信息 查询对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "接入客户端信息")
public class OauthClientDetailsQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端标识
	 */
	@Schema(title = "客户端标识", description = "客户端标识")
	private String clientId;

}
