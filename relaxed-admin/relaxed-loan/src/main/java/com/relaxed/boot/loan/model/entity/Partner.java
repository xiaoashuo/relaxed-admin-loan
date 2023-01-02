package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 合作方
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@TableName("t_partner")
@Schema(title = "合作方")
public class Partner {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	@Schema(title = "主键", description = "主键")
	private Integer partnerId;

	/**
	 * 合作方别名
	 */
	@Schema(title = "合作方别名", description = "合作方别名")
	private String partnerAlias;

	/**
	 * 是否有效(0:未删除 1 或时间戳为已删除)
	 */
	@Schema(title = "是否有效(0:未删除 1 或时间戳为已删除)", description = "是否有效(0:未删除 1 或时间戳为已删除)")
	private Integer deleted;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;

}
