package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.vo.TemplatePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模板文件配置表模型转换器
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Mapper
public interface TemplateConverter {

	TemplateConverter INSTANCE = Mappers.getMapper(TemplateConverter.class);

	/**
	 * PO 转 PageVO
	 * @param template 模板文件配置表
	 * @return TemplatePageVO 模板文件配置表PageVO
	 */
	TemplatePageVO poToPageVo(Template template);

}
