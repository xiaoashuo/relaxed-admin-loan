package com.relaxed.boot.loan.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 模板文件配置表分页视图对象
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Data
@Schema(title = "模板文件配置表分页视图对象")
public class TemplatePageVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 模板主键id
	 */
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
	 * 模板原始文件名称
	 */
	@Schema(title = "模板原始文件名称", description = "模板原始文件名称")
	private String templateFilename;

	/**
	 * 模板访问地址
	 */
	@Schema(title = "模板访问地址", description = "模板访问地址")
	private String templateUrl;

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
	@Schema(title = "创建时间", description = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 更新时间
	 */
	@Schema(title = "更新时间", description = "更新时间")
	private LocalDateTime updatedTime;

}
