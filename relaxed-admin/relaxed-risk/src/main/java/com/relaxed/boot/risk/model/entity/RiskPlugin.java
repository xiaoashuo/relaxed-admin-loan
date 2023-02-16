package com.relaxed.boot.risk.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Yakir
 * @since 2021-09-28T13:43:11.834
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("engine_plugin")
@Schema(title = "")
public class RiskPlugin extends Model<RiskPlugin> {

	/**
	 * 主键id
	 */
	@TableId(value = "id")
	@Schema(title = "主键id", description = "主键id")
	private Long id;

	/**
	 * 插件名称
	 */
	@Schema(title = "插件名称", description = "插件名称")
	private String pluginName;

	/**
	 * 插件描述
	 */
	@Schema(title = "插件描述", description = "插件描述")
	private String pluginDesc;

	/**
	 * 插件元数据
	 */
	@Schema(title = "插件元数据", description = "插件元数据")
	private String pluginMeta;

	/**
	 * 插件状态 1启用 0禁用
	 */
	@Schema(title = "插件状态", description = "插件状态")
	private Integer status;

	/**
	 * 创建时间
	 */
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 更新时间
	 */
	@Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;

}
