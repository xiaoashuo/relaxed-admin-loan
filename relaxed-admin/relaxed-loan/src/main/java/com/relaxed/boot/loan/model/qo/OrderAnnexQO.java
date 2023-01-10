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
public class OrderAnnexQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件主键
	 */
	@Schema(title = "文件主键", description = "文件主键")
	private Long fileId;
	/**
	 * 订单号
	 */
	@Schema(title = "订单号", description = "订单号")
	private Long orderId;
	@Schema(title = " 文件名", description = " 文件名")
	private String fileName;
}
