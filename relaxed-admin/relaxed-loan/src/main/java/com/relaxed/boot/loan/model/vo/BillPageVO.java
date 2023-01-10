package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 分页视图对象
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Data
@Schema(title = "分页视图对象")  
public class BillPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 账单编号
     */
    @Schema(title = "账单编号", description = "账单编号")
    private Long billId;
    
    /**
     * 借款编号,用于关联t_loan表
     */
    @Schema(title = "借款编号,用于关联t_loan表", description = "借款编号,用于关联t_loan表")
    private Long loanId;
    
    /**
     * 合作方账单编号
     */
    @Schema(title = "合作方账单编号", description = "合作方账单编号")
    private String partnerBillNo;
    
    /**
     * 账单期号
     */
    @Schema(title = "账单期号", description = "账单期号")
    private Integer termNo;
    
    /**
     * 开始日期
     */
    @Schema(title = "开始日期", description = "开始日期")
    private LocalDateTime startDate;
    
    /**
     * 结束日期
     */
    @Schema(title = "结束日期", description = "结束日期")
    private LocalDateTime endDate;
    
    /**
     * 最后还款日（含宽限期）
     */
    @Schema(title = "最后还款日（含宽限期）", description = "最后还款日（含宽限期）")
    private LocalDateTime dueDate;
    
    /**
     * 结清日期
     */
    @Schema(title = "结清日期", description = "结清日期")
    private LocalDateTime clearDate;
    
    /**
     * 账单类型1：正常账单 2：提前清贷账单 3：降贷账单  4：回购账单99：债转账单
     */
    @Schema(title = "账单类型1：正常账单 2：提前清贷账单 3：降贷账单  4：回购账单99：债转账单", description = "账单类型1：正常账单 2：提前清贷账单 3：降贷账单  4：回购账单99：债转账单")
    private Integer billType;
    
    /**
     * 本期状态1:正常 2:逾期 5:已结清
     */
    @Schema(title = "本期状态1:正常 2:逾期 5:已结清", description = "本期状态1:正常 2:逾期 5:已结清")
    private Integer termStatus;
    
    /**
     * 本期应收金额
     */
    @Schema(title = "本期应收金额", description = "本期应收金额")
    private BigDecimal billReceivableAmt;
    
    /**
     * 本期实收金额（含减免金额）
     */
    @Schema(title = "本期实收金额（含减免金额）", description = "本期实收金额（含减免金额）")
    private BigDecimal billReceiptsAmt;
    
    /**
     * 本期减免金额
     */
    @Schema(title = "本期减免金额", description = "本期减免金额")
    private BigDecimal billMitigateAmt;
    
    /**
     * 最后同步时间
     */
    @Schema(title = "最后同步时间", description = "最后同步时间")
    private LocalDateTime latestSyncTime;
    
    /**
     * 逻辑删除标识，未删除为 0，已删除为删除时间
     */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
    private Integer valid;
    
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
