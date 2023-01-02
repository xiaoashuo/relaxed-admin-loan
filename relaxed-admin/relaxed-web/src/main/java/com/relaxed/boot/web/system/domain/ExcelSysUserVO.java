package com.relaxed.boot.web.system.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Yakir
 * @Topic ExcelSysUserVO
 * @Description
 * @date 2022/12/9 13:40
 * @Version 1.0
 */
@Schema(title = "Excel用户实体", description = "Excel用户实体")
@Data
public class ExcelSysUserVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@ExcelProperty(value = "主键id", index = 0)
	@Schema(title = "主键id", description = "主键id")
	private Integer userId;

	/**
	 * 登录账号
	 */
	@ExcelProperty(value = "登录账号", index = 1)
	@Schema(title = "登录账号", description = "登录账号")
	private String username;

	/**
	 * 显示名称
	 */
	@ExcelProperty(value = "显示名称", index = 2)
	@Schema(title = "显示名称", description = "显示名称")
	private String nickname;

	/**
	 * 性别(0-默认未知,1-男,2-女)
	 */
	@ExcelProperty(value = "性别(0-默认未知,1-男,2-女)", index = 3)
	@Schema(title = "性别(0-默认未知,1-男,2-女)", description = "性别(0-默认未知,1-男,2-女)")
	private Integer sex;

	/**
	 * 电子邮件
	 */
	@ExcelProperty(value = "电子邮件", index = 4)
	@Schema(title = "电子邮件", description = "电子邮件")
	private String email;

	/**
	 * 电话
	 */
	@ExcelProperty(value = "电话", index = 5)
	@Schema(title = "电话", description = "电话")
	private String phone;

	/**
	 * 状态(1-正常,0-冻结)
	 */
	@ExcelProperty(value = "状态(1-正常,0-冻结)", index = 6)
	@Schema(title = "状态(1-正常,0-冻结)", description = "状态(1-正常,0-冻结)")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@ExcelProperty(value = "创建时间", index = 7)
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

}
