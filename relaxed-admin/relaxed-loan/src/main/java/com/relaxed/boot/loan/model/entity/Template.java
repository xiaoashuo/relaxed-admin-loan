package com.relaxed.boot.loan.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 模板文件配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@TableName("t_template")
@Schema(title = "模板文件配置表")  
public class Template {

	private static final long serialVersionUID = 1L;

	/**
	 * 模板主键id
	 */
	@TableId
    @Schema(title = "模板主键id", description = "模板主键id")
	private Integer templateId;
    
	/**
	 * 模板代号
	 */
    @Schema(title = "模板代号", description = "模板代号")
	private String templateCode;
    
	/**
	 * 模板名称
	 */
    @Schema(title = "模板名称", description = "模板名称")
	private String templateName;
    
	/**
	 * 模板文件(填充模板)存放路径
	 */
    @Schema(title = "模板文件(填充模板)存放路径", description = "模板文件(填充模板)存放路径")
	private String templatePath;
    
	/**
	 * 数据文件(导入模板)存放路径(如有)
	 */
    @Schema(title = "数据文件(导入模板)存放路径(如有)", description = "数据文件(导入模板)存放路径(如有)")
	private String datafilePath;
    
	/**
	 * 文件类型1.服务信托2.财富端确认书
	 */
    @Schema(title = "文件类型1.服务信托2.财富端确认书", description = "文件类型1.服务信托2.财富端确认书")
	private String fileType;
    
	/**
	 * 模板描述
	 */
    @Schema(title = "模板描述", description = "模板描述")
	private String remark;
    
	/**
	 * 创建人
	 */
    @Schema(title = "创建人", description = "创建人")
	private Integer createBy;
    
	/**
	 * 修改人
	 */
    @Schema(title = "修改人", description = "修改人")
	private Integer updateBy;
    
	/**
	 * 逻辑删除标识，未删除为 0，已删除为删除时间
	 */
    @Schema(title = "逻辑删除标识，未删除为 0，已删除为删除时间", description = "逻辑删除标识，未删除为 0，已删除为删除时间")
	private Long deleted;
    
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
