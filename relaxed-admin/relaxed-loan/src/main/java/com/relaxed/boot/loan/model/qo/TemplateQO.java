package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 模板文件配置表 查询对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "模板文件配置表")  
public class TemplateQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 模板主键id
	 */
	@Schema(title = "模板主键id", description = "模板主键id")
	private Integer templateId;

}