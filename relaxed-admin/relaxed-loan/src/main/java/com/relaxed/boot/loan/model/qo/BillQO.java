package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 查询对象
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@Schema(title = "")
public class BillQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 账单编号
	 */
	@Schema(title = "账单编号", description = "账单编号")
	private Long billId;

	@NotNull(message = "借据编号不存在")
	@Schema(title = "借款编号", description = "借款编号")
	private Long loanId;

}
