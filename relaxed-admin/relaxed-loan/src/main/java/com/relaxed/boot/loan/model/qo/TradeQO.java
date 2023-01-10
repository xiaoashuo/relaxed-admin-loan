package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 *  查询对象
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@Schema(title = "")  
public class TradeQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 交易编号
	 */
	@Schema(title = "交易编号", description = "交易编号")
	private Long tradeId;

}