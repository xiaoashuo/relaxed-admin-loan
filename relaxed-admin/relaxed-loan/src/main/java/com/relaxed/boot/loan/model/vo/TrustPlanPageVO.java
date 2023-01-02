package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 信托计划配置表分页视图对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "信托计划配置表分页视图对象")
public class TrustPlanPageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Schema(title = "主键", description = "主键")
	private Integer trustPlanId;

	/**
	 * 信托计划名称
	 */
	@Schema(title = "信托计划名称", description = "信托计划名称")
	private String trustPlanName;

	/**
	 * 信托计划编号
	 */
	@Schema(title = "信托计划编号", description = "信托计划编号")
	private String trustPlanNo;

	/**
	 * 信托计划成立日期
	 */
	@Schema(title = "信托计划成立日期", description = "信托计划成立日期")
	private LocalDateTime establishedTime;

	/**
	 * 信托计划到期日期
	 */
	@Schema(title = "信托计划到期日期", description = "信托计划到期日期")
	private LocalDateTime expireTime;

	/**
	 * 信托计划摊还期开始日期
	 */
	@Schema(title = "信托计划摊还期开始日期", description = "信托计划摊还期开始日期")
	private LocalDateTime repayTime;

	/**
	 * 信托计划预期收益率
	 */
	@Schema(title = "信托计划预期收益率", description = "信托计划预期收益率")
	private String earningRate;

	/**
	 * 信托计划初始规模
	 */
	@Schema(title = "信托计划初始规模", description = "信托计划初始规模")
	private String fundPool;

	/**
	 * 是否有效(0:未删除 1 或时间戳为已删除)
	 */
	@Schema(title = "是否有效(0:未删除 1 或时间戳为已删除)", description = "是否有效(0:未删除 1 或时间戳为已删除)")
	private Integer deleted;

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