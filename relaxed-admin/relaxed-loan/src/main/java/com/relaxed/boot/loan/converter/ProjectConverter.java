package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Project;
import com.relaxed.boot.loan.model.vo.ProjectPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 产品配置表模型转换器
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Mapper
public interface ProjectConverter {

	ProjectConverter INSTANCE = Mappers.getMapper(ProjectConverter.class);

	/**
	 * PO 转 PageVO
	 * @param project 产品配置表
	 * @return ProjectPageVO 产品配置表PageVO
	 */
	ProjectPageVO poToPageVo(Project project);

}
