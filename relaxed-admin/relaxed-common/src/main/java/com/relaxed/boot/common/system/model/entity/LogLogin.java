package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 登陆日志
 *
 * @author Yakir 2022-11-22 14:02:00
 */
@Data
@TableName("log_login")
@Schema(title = "登陆日志")
public class LogLogin {

	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	@Schema(title = "编号", description = "编号")
	private Long id;

	/**
	 * 追踪ID
	 */
	@Schema(title = "追踪ID", description = "追踪ID")
	private String traceId;

	/**
	 * 用户名
	 */
	@Schema(title = "用户名", description = "用户名")
	private String username;

	/**
	 * 登陆IP
	 */
	@Schema(title = "登陆IP", description = "登陆IP")
	private String ip;

	/**
	 * 操作系统
	 */
	@Schema(title = "操作系统", description = "操作系统")
	private String os;

	/**
	 * 状态
	 */
	@Schema(title = "状态", description = "状态")
	private Integer status;

	/**
	 * 事件类型，1：登录 2：登出
	 */
	@Schema(title = "事件类型，1：登录 2：登出", description = "事件类型，1：登录 2：登出")
	private Integer eventType;

	/**
	 * 操作信息
	 */
	@Schema(title = "操作信息", description = "操作信息")
	private String msg;

	/**
	 * 登陆地点
	 */
	@Schema(title = "登陆地点", description = "登陆地点")
	private String location;

	/**
	 * 浏览器
	 */
	@Schema(title = "浏览器", description = "浏览器")
	private String browser;

	/**
	 * 登录/登出时间
	 */
	@Schema(title = "登录/登出时间", description = "登录/登出时间")
	private LocalDateTime loginTime;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

}
