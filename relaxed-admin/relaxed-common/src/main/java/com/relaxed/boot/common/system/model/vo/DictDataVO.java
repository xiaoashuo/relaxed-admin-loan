package com.relaxed.boot.common.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Yakir
 * @Topic DictDataVO
 * @Description
 * @date 2022/11/11 15:37
 * @Version 1.0
 */
@Data
@Schema(title = "字典数据VO")
public class DictDataVO {

	/**
	 * 字典标识
	 */
	@Schema(title = "字典标识", description = "字典标识")
	private String dictCode;

	/**
	 * 字典值类型
	 */
	@Schema(title = "字典值类型", description = "字典值类型")
	private Integer valueType;

	/**
	 * 字典Hash值
	 */
	@Schema(title = "字典Hash值", description = "字典Hash值")
	private String hashCode;

	/**
	 * 字典项列表
	 */
	@Schema(title = "字典项列表", description = "字典项列表")
	private List<DictItemVO> dictItems;

}
