package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 *  查询对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "")  
public class OrderBankCardQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 银行卡主键
	 */
	@Schema(title = "银行卡主键", description = "银行卡主键")
	private Long bankCardId;
	@Schema(title = "主键id", description = "主键id")
	private Long orderId;
}
