package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 签章记录 查询对象
 *
 * @author yakir 2023-01-29 09:31:45
 */
@Data
@Schema(title = "签章记录")  
public class StampRecordQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 签章记录id
	 */
	@Schema(title = "签章记录id", description = "签章记录id")
	private Integer stampRecordId;

}