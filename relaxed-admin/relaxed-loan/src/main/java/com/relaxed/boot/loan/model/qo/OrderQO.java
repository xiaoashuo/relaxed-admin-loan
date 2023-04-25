package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 查询对象
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@Schema(title = "")
public class OrderQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Schema(title = "主键id", description = "主键id")
	private Long orderId;

	@Schema(title = "合作方业务号", description = "合作方业务号")
	private String partnerBizNo;

}
