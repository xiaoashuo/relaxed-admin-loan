package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@TableName("t_bill_item")
@Schema(title = "")  
public class BillItem {

	private static final long serialVersionUID = 1L;

	/**
	 * 账单科目表
	 */
	@TableId
    @Schema(title = "账单科目表", description = "账单科目表")
	private Long billItemId;
    
	/**
	 * 借款编号,用于关联t_loan表
	 */
    @Schema(title = "借款编号,用于关联t_loan表", description = "借款编号,用于关联t_loan表")
	private Long loanId;
    
	/**
	 * 账单编号,用于关联t_bill表
	 */
    @Schema(title = "账单编号,用于关联t_bill表", description = "账单编号,用于关联t_bill表")
	private Long billId;
    

    
	/**
	 * 科目类型（1: 本金 11: 利息 21: 罚息）
	 */
    @Schema(title = "科目类型（1: 本金 11: 利息 21: 罚息）", description = "科目类型（1: 本金 11: 利息 21: 罚息）")
	private Integer itemType;
    
	/**
	 * 应收金额
	 */
    @Schema(title = "应收金额", description = "应收金额")
	private BigDecimal receivableAmt;
    
	/**
	 * 实收金额
	 */
    @Schema(title = "实收金额", description = "实收金额")
	private BigDecimal receiptsAmt;
    
	/**
	 * 逻辑删除标识，未删除为 0，已删除为删除时间
	 */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
	private Integer valid;
    
	/**
	 * 最后同步时间
	 */
    @Schema(title = "最后同步时间", description = "最后同步时间")
	private LocalDateTime latestSyncTime;
    
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
