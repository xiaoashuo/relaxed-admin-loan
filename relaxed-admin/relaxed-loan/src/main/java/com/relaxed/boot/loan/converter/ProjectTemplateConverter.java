package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.vo.ProjectTemplatePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 项目模板配置表模型转换器
 *
 * @author yakir 2023-01-02 09:55:28
 */
@Mapper
public interface ProjectTemplateConverter {

	ProjectTemplateConverter INSTANCE = Mappers.getMapper(ProjectTemplateConverter.class);

	/**
	 * PO 转 PageVO
	 * @param projectTemplate 项目模板配置表
	 * @return ProjectTemplatePageVO 项目模板配置表PageVO
	 */
	ProjectTemplatePageVO poToPageVo(ProjectTemplate projectTemplate);

}
