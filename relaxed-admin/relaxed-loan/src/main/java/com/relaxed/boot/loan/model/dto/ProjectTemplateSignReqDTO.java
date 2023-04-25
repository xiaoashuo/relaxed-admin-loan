package com.relaxed.boot.loan.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Yakir
 * @Topic ProjectTemplateSignReqDTO
 * @Description
 * @date 2023/1/7 12:38
 * @Version 1.0
 */
@Data
public class ProjectTemplateSignReqDTO {

	@Schema(title = "主键", description = "主键")
	private Integer projectTemplateId;

	@Schema(description = "签章方式 1模板2关键字")
	private Integer sealWay;

	/**
	 * 签章id
	 */
	@Schema(title = "签章id", description = "签章id")
	private Integer sealId;

	/**
	 * 证书id
	 */
	@Schema(title = "证书id", description = "证书id")
	private Integer keystoreId;

	/**
	 * 签章坐标[{pageNum:1,locations:[{left:0,top:0,width:100,height:100}}]]
	 */
	@Schema(title = "签章坐标[{pageNum:1,locations:[{left:0,top:0,width:100,height:100}}]]",
			description = "签章坐标[{pageNum:1,locations:[{left:0,top:0,width:100,height:100}}]]")
	private String sealLocation;

	/**
	 * 关键字
	 */
	@Schema(title = "关键字", description = "关键字")
	private String sealKeyword;

}
