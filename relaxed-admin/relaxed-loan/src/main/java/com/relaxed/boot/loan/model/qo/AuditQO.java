package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 审核表 查询对象
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@Schema(title = "审核表")
public class AuditQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Long auditId;

}