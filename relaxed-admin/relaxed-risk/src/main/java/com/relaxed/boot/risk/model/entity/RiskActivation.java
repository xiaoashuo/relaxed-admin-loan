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
 * @since 2021-08-29T18:48:19.435
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("risk_activation")
@Schema(title = "")
public class RiskActivation extends Model<RiskActivation> {

	/**
	 * 主键
	 */
	@TableId(value = "ID")
	@Schema(title = "主键", description = "主键")
	private Long id;

	/**
	 * 名称
	 */
	@Schema(title = "名称", description = "名称")
	private String activationName;

	/**
	 *
	 */
	@Schema(title = "", description = "")
	private String label;

	/**
	 * model id
	 */
	@Schema(title = "model id", description = "model id")
	private Long modelId;

	/**
	 * 注释
	 */
	@Schema(title = "注释", description = "注释")
	private String comment;

	/**
	 * 底部阀值
	 */
	@Schema(title = "底部阀值", description = "底部阀值")
	private Integer bottom;

	/**
	 * 中间阀值
	 */
	@Schema(title = "中间阀值", description = "中间阀值")
	private Integer median;

	/**
	 * 顶部阀值
	 */
	@Schema(title = "顶部阀值", description = "顶部阀值")
	private Integer high;

	/**
	 * 状态
	 */
	@Schema(title = "状态", description = "状态")
	private Integer status;

	/**
	 *
	 */
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 *
	 */
	@Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;

}
