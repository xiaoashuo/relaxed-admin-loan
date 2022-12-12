package com.relaxed.boot.common.system.model.qo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 组织架构 查询对象
 *
 * @author Yakir 2022-11-23 17:56:28
 */
@Data
@Schema(title = "组织架构")
public class SysOrganizationQO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Schema(title = "ID", description = "ID")
	private Integer id;

	/**
	 * 组织名称
	 */
	@Schema(title = "组织名称", description = "组织名称")
	private String name;

}
