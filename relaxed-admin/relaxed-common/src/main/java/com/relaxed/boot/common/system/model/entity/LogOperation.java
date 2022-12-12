package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Accessors(chain = true)
@Data
@TableName("log_operation")
@Schema(title = "操作日志")
public class LogOperation {

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
	 * 日志消息
	 */
	@Schema(title = "日志消息", description = "日志消息")
	private String msg;

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
	 * 请求方式
	 */
	@Schema(title = "请求方式", description = "请求方式")
	private String method;

	/**
	 * 操作提交的数据
	 */
	@Schema(title = "操作提交的数据", description = "操作提交的数据")
	private String params;

	/**
	 * 操作结果
	 */
	@Schema(title = "操作结果", description = "操作结果")
	private String result;

	/**
	 * 操作状态
	 */
	@Schema(title = "操作状态", description = "操作状态")
	private Integer status;

	/**
	 * 操作类型
	 */
	@Schema(title = "操作类型", description = "操作类型")
	private Integer type;

	/**
	 * 执行时长
	 */
	@Schema(title = "执行时长", description = "执行时长")
	private Long time;

	/**
	 * 创建者
	 */
	@Schema(title = "创建者", description = "创建者")
	private String operator;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

}
