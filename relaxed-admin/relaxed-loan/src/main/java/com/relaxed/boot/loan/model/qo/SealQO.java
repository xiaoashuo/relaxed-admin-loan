package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 签章图片表 查询对象
 *
 * @author yakir 2022-12-15 10:25:25
 */
@Data
@Schema(title = "签章图片表")  
public class SealQO  {
	private static final long serialVersionUID = 1L;

	/**
	 * 签章图片主键id
	 */
	@Schema(title = "签章图片主键id", description = "签章图片主键id")
	private Integer sealId;

}