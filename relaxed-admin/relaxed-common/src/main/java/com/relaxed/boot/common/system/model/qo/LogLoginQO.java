package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 登陆日志 查询对象
 *
 * @author Yakir 2022-11-22 14:02:00
 */
@Data
@Schema(title = "登陆日志")
public class LogLoginQO {

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