package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 查询对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "")
public class BillItemFillerQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 填充项编号
	 */
	@Schema(title = "填充项编号", description = "填充项编号")
	private Long itemFillerId;

	@Schema(title = "账单科目表", description = "账单科目表")
	private Long billItemId;

}
