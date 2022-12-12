package com.relaxed.boot.common.system.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.relaxed.common.core.validation.constraints.OneOfInts;
import com.relaxed.common.core.validation.group.GroupType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Map;

/**
 * @author Yakir
 * @Topic SysDictItemDTO
 * @Description
 * @date 2022/11/11 15:44
 * @Version 1.0
 */
@Data
@Schema(title = "字典项")
public class SysDictItemDTO {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@Null(message = "id 必须为空", groups = GroupType.CreateGroup.class)
	@NotNull(message = "id 不能为空", groups = GroupType.UpdateGroup.class)
	@Schema(title = "ID")
	private Integer id;

	/**
	 * 字典标识
	 */
	@NotBlank(message = "dictCode 不能为空")
	@Schema(title = "字典标识")
	private String dictCode;

	/**
	 * 数据值
	 */
	@NotBlank(message = "value 不能为空")
	@Schema(title = "数据值")
	private String value;

	/**
	 * 文本值
	 */
	@NotBlank(message = "name 不能为空")
	@Schema(title = "文本值")
	private String name;

	/**
	 * 状态
	 */
	@NotNull(message = "status 不能为空", groups = GroupType.CreateGroup.class)
	@OneOfInts(value = { 1, 0 }, message = "status 必须在[0-1]", allowNull = true)
	@Schema(title = "状态", description = "1：启用 0：禁用")
	private Integer status;

	/**
	 * 附加属性值
	 */
	@TableField(typeHandler = JacksonTypeHandler.class)
	@Schema(title = "附加属性值")
	private Map<String, Object> attributes;

	/**
	 * 排序（升序）
	 */
	@NotNull
	@Min(value = 0)
	@Schema(title = "排序（升序）")
	private Integer sort;

	/**
	 * 备注
	 */
	@Schema(title = "备注")
	private String remarks;

}
