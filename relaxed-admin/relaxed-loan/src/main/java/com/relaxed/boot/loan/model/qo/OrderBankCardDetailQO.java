package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 订单银行卡放款详情表 查询对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "订单银行卡放款详情表")  
public class OrderBankCardDetailQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 银行卡主键
	 */
	@Schema(title = "银行卡主键", description = "银行卡主键")
	private Long detailId;

}