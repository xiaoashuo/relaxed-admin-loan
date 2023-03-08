package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 省市区码表
 *
 * @author Yakir 2023-03-08 10:44:09
 */
@Data
@TableName("t_province")
@Schema(title = "省市区码表")  
public class Province {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
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
