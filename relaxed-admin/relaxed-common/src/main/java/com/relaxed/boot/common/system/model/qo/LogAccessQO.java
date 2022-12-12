package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 访问日志 查询对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "访问日志")
public class LogAccessQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 追踪ID
	 */
	@Schema(title = "追踪ID", description = "追踪ID")
	private String traceId;

	/**
	 * 登陆IP
	 */
	@Schema(title = "登陆IP", description = "登陆IP")
	private String ip;


}
