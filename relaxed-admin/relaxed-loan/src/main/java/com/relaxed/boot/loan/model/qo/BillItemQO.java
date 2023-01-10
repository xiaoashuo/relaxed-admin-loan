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
public class BillItemQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 账单科目表
	 */
	@Schema(title = "账单科目表", description = "账单科目表")
	private Long billItemId;

}