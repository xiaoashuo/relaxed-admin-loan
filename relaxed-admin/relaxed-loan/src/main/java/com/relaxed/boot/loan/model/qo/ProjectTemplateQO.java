package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 项目模板配置表 查询对象
 *
 * @author yakir 2023-01-02 09:55:28
 */
@Data
@Schema(title = "项目模板配置表")  
public class ProjectTemplateQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Integer projectTemplateId;
	@NotNull(message = "项目id不能为空")
	@Schema(title = "项目id", description = "项目id")
	private Integer projectId;

}