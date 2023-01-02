package com.relaxed.boot.loan.model.qo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 签章证书表 查询对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "签章证书表")
public class CertificateQO {

	private static final long serialVersionUID = 1L;

	/**
	 * 签章证书主键id
	 */
	@Schema(title = "签章证书主键id", description = "签章证书主键id")
	private Integer certificateId;

}