package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 担保机构 查询对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "担保机构")  
public class OrderGuarantorQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 关联人主键
	 */
	@Schema(title = "关联人主键", description = "关联人主键")
	private Long guarantorId;

}