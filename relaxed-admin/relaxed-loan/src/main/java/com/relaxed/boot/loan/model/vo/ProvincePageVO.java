package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 省市区码表分页视图对象
 *
 * @author Yakir 2023-03-08 10:44:09
 */
@Data
@Schema(title = "省市区码表分页视图对象")  
public class ProvincePageVO {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键", description = "主键")
    private Integer provinceId;
    
    /**
     * 区域code
     */
    @Schema(title = "区域code", description = "区域code")
    private String regionCode;
    
    /**
     * 区域名称
     */
    @Schema(title = "区域名称", description = "区域名称")
    private String regionName;
    
    /**
     * 区域级别
     */
    @Schema(title = "区域级别", description = "区域级别")
    private Integer regionLevel;
    
    /**
     * 父级区域
     */
    @Schema(title = "父级区域", description = "父级区域")
    private String parentCode;
    
    /**
     * 是否有效（0:无效 1:有效）
     */
    @Schema(title = "是否有效（0:无效 1:有效）", description = "是否有效（0:无效 1:有效）")
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