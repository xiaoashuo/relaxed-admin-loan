package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 共同借款人 查询对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "共同借款人")
public class OrderBorrowQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联人主键
	 */
	@Schema(title = "关联人主键", description = "关联人主键")
	private Long userId;

	/**
	 * 订单号
	 */
	@Schema(title = "订单号", description = "订单号")
	private Long orderId;

	/**
	 * 关联人姓名
	 */
	@Schema(title = "关联人姓名", description = "关联人姓名")
	private String relatedName;

}
