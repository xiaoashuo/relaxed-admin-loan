package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 字典项
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Data
@TableName("sys_dict_item")
@Schema(title = "字典项")
public class SysDictItem {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	@Schema(title = "ID", description = "ID")
	private Integer id;

	/**
	 * 字典Code
	 */
	@Schema(title = "字典Code", description = "字典Code")
	private String dictCode;

	/**
	 * 数据值
	 */
	@Schema(title = "数据值", description = "数据值")
	private String value;

	/**
	 * 标签
	 */
	@Schema(title = "标签", description = "标签")
	private String name;

	/**
	 * 状态 1：启用 0：禁用
	 */
	@Schema(title = "状态 1：启用 0：禁用", description = "状态 1：启用 0：禁用")
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
	@Schema(title = "排序（升序）", description = "排序（升序）")
	private Integer sort;

	/**
	 * 备注
	 */
	@Schema(title = "备注", description = "备注")
	private String remarks;

	/**
	 * 逻辑删除标识，未删除为 0，已删除为删除时间
	 */
	@Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
	private Long deleted;

	/**
	 * 创建人
	 */
	@Schema(title = "创建人", description = "创建人")
	private Integer createBy;

	/**
	 * 修改人
	 */
	@Schema(title = "修改人", description = "修改人")
	private Integer updateBy;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(title = "修改时间", description = "修改时间")
	private LocalDateTime updatedTime;

}
