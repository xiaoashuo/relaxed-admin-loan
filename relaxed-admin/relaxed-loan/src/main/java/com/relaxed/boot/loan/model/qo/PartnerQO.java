package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 合作方 查询对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "合作方")
public class PartnerQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Integer partnerId;

}