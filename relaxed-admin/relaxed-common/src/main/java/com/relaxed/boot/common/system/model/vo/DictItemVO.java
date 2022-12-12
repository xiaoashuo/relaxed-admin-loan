package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * @author Yakir
 * @Topic DictItemVO
 * @Description
 * @date 2022/11/11 15:37
 * @Version 1.0
 */
@Data
@Schema(title = "字典项VO")
public class DictItemVO {

	@Schema(title = "id", description = "id")
	private Integer id;

	/**
	 * 数据值
	 */
	@Schema(title = "数据值", description = "数据值")
	private String value;

	/**
	 * 标签
	 */
	@Schema(title = "文本值", description = "文本值")
	private String name;

	/**
	 * 状态
	 */
	@Schema(title = "状态", description = "1：启用 0：禁用")
	private Integer status;

	/**
	 * 附加属性值
	 */
	@Schema(title = "附加属性值", description = "附加属性值")
	private Map<String, Object> attributes;

}
