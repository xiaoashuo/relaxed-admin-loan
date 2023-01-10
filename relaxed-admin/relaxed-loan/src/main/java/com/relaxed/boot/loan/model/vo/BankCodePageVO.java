package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 银行编码表分页视图对象
 *
 * @author yakir 2023-01-10 10:13:02
 */
@Data
@Schema(title = "银行编码表分页视图对象")  
public class BankCodePageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(title = "主键id", description = "主键id")
    private Integer bankCodeId;
    
    /**
     * 银行编码
     */
    @Schema(title = "银行编码", description = "银行编码")
    private String bankCode;
    
    /**
     * 银行名称
     */
    @Schema(title = "银行名称", description = "银行名称")
    private String bankName;
    
    /**
     * 描述
     */
    @Schema(title = "描述", description = "描述")
    private String remark;
    
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