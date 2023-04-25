package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 借款合同表 查询对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "借款合同表")
public class LoanQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 借款编号
	 */
	@Schema(title = "借款编号", description = "借款编号")
	private Long loanId;

}