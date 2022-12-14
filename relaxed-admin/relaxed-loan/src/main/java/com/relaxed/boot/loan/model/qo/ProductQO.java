package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 信托产品表 查询对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "信托产品表")  
public class ProductQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Integer productId;

}