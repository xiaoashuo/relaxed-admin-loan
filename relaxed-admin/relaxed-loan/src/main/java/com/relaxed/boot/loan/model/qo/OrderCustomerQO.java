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
public class OrderCustomerQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户编号，关联t_customer表
	 */
	@Schema(title = "客户编号，关联t_customer表", description = "客户编号，关联t_customer表")
	private Long customerId;

}