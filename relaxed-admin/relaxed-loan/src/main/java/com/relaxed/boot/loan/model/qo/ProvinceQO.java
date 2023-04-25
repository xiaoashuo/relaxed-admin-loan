package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 省市区码表 查询对象
 *
 * @author Yakir 2023-03-08 10:44:09
 */
@Data
@Schema(title = "省市区码表")
public class ProvinceQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Integer provinceId;

}