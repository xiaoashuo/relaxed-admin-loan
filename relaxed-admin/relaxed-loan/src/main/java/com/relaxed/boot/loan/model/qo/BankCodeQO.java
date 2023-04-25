package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 银行编码表 查询对象
 *
 * @author yakir 2023-01-10 10:13:02
 */
@Data
@Schema(title = "银行编码表")
public class BankCodeQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Schema(title = "主键id", description = "主键id")
	private Integer bankCodeId;

	/**
	 * 银行编码
	 */
	@Schema(title = "银行编码", description = "银行编码")
	private String bankCode;

	/**
	 * 银行名称
	 */
	@Schema(title = "银行名称", description = "银行名称")
	private String bankName;

}
