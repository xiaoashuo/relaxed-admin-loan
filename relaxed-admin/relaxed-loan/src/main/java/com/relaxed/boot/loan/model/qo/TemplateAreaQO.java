package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 模板文件文本域表 查询对象
 *
 * @author Yakir 2022-12-16 11:18:37
 */
@Data
@Schema(title = "模板文件文本域表")  
public class TemplateAreaQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 模板参数主键
	 */
	@NotNull(message="模板id不能为空")
	@Schema(title = "模板id", description = "模板id")
	private Integer templateId;

}
