package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 分页视图对象
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Data
@Schema(title = "分页视图对象")  
public class BillItemFillerPageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 填充项编号
     */
    @Schema(title = "填充项编号", description = "填充项编号")
    private Long itemFillerId;
    
    /**
     * 借款编号,用于关联t_loan表
     */
    @Schema(title = "借款编号,用于关联t_loan表", description = "借款编号,用于关联t_loan表")
    private Long loanId;
    
    /**
     * 账单id,用于关联t_bill表
     */
    @Schema(title = "账单id,用于关联t_bill表", description = "账单id,用于关联t_bill表")
    private Long billId;
    
    /**
     * 账单科目id,用于关联t_bill_item表
     */
    @Schema(title = "账单科目id,用于关联t_bill_item表", description = "账单科目id,用于关联t_bill_item表")
    private Long billItemId;
    
    /**
     * 填充名称（可自定义）
     */
    @Schema(title = "填充名称（可自定义）", description = "填充名称（可自定义）")
    private String fillerName;
    
    /**
     * 填充类型：2- 减免 11- 实收
     */
    @Schema(title = "填充类型：2- 减免 11- 实收", description = "填充类型：2- 减免 11- 实收")
    private Integer fillerType;
    
    /**
     * 填充金额（应收增加为正、应收减少为负、实收增加为正、实收减少为负）
     */
    @Schema(title = "填充金额（应收增加为正、应收减少为负、实收增加为正、实收减少为负）", description = "填充金额（应收增加为正、应收减少为负、实收增加为正、实收减少为负）")
    private BigDecimal fillerAmount;
    
    /**
     * 填充目标：1- 应收2- 实收
     */
    @Schema(title = "填充目标：1- 应收2- 实收", description = "填充目标：1- 应收2- 实收")
    private Integer fillerTarget;
    
    /**
     * 交易事件：11- 逾期还款12- 正常还款13- 提前还款14- 提前结清
     */
    @Schema(title = "交易事件：11- 逾期还款12- 正常还款13- 提前还款14- 提前结清", description = "交易事件：11- 逾期还款12- 正常还款13- 提前还款14- 提前结清")
    private Integer repayEvent;
    
    /**
     * 交易编号，关联t_trade表
     */
    @Schema(title = "交易编号，关联t_trade表", description = "交易编号，关联t_trade表")
    private Long tradeId;
    
    /**
     * 操作者编号：0- 系统
     */
    @Schema(title = "操作者编号：0- 系统", description = "操作者编号：0- 系统")
    private Integer operatorId;
    
    /**
     * 说明
     */
    @Schema(title = "说明", description = "说明")
    private String remark;
    
    /**
     * 账单填充时间
     */
    @Schema(title = "账单填充时间", description = "账单填充时间")
    private LocalDateTime partnerFillerTime;
    
    /**
     * 保险报案号
     */
    @Schema(title = "保险报案号", description = "保险报案号")
    private String reportNo;
    
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
