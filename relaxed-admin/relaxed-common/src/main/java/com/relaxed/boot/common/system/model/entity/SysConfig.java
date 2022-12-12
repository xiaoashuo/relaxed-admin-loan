package com.relaxed.boot.common.system.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 基础配置
 *
 * @author yakir 2022-11-15 10:24:40
 */
@Data
@TableName("sys_config")
@Schema(title = "基础配置")
public class SysConfig {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	@Schema(title = "", description = "")
	private Integer id;

	/**
	 * 配置名称
	 */
	@Schema(title = "配置名称", description = "配置名称")
	private String name;

	/**
	 * 配置键
	 */
	@Schema(title = "配置键", description = "配置键")
	private String confKey;

	/**
	 * 配置值
	 */
	@Schema(title = "配置值", description = "配置值")
	private String confValue;

	/**
	 * 分类
	 */
	@Schema(title = "分类", description = "分类")
	private String category;

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
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 修改时间
	 */
	@Schema(title = "修改时间", description = "修改时间")
	private LocalDateTime updatedTime;
}
