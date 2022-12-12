package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 访问日志分页视图对象
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Data
@Schema(title = "访问日志分页视图对象")
public class LogAccessPageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@Schema(title = "编号", description = "编号")
	private Long id;

	/**
	 * 追踪ID
	 */
	@Schema(title = "追踪ID", description = "追踪ID")
	private String traceId;

	/**
	 * 用户ID
	 */
	@Schema(title = "用户ID", description = "用户ID")
	private Long userId;

	/**
	 * 用户名
	 */
	@Schema(title = "用户名", description = "用户名")
	private String username;

	/**
	 * 访问IP地址
	 */
	@Schema(title = "访问IP地址", description = "访问IP地址")
	private String ip;

	/**
	 * 用户代理
	 */
	@Schema(title = "用户代理", description = "用户代理")
	private String userAgent;

	/**
	 * 请求URI
	 */
	@Schema(title = "请求URI", description = "请求URI")
	private String uri;

	/**
	 * 请求映射路径
	 */
	@Schema(title = "请求映射路径", description = "请求映射路径")
	private String matchingPattern;

	/**
	 * 操作方式
	 */
	@Schema(title = "操作方式", description = "操作方式")
	private String method;

	/**
	 * 请求参数
	 */
	@Schema(title = "请求参数", description = "请求参数")
	private String reqParams;

	/**
	 * 请求body
	 */
	@Schema(title = "请求body", description = "请求body")
	private String reqBody;

	/**
	 * 响应状态码
	 */
	@Schema(title = "响应状态码", description = "响应状态码")
	private Integer httpStatus;

	/**
	 * 响应信息
	 */
	@Schema(title = "响应信息", description = "响应信息")
	private String result;

	/**
	 * 错误消息
	 */
	@Schema(title = "错误消息", description = "错误消息")
	private String errorMsg;

	/**
	 * 执行时长
	 */
	@Schema(title = "执行时长", description = "执行时长")
	private Long time;

	/**
	 * 创建时间
	 */
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

}
