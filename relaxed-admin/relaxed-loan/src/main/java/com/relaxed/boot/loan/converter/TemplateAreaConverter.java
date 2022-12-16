package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.TemplateArea;
import com.relaxed.boot.loan.model.vo.TemplateAreaPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模板文件文本域表模型转换器
 *
 * @author Yakir 2022-12-16 11:18:37
 */
@Mapper
public interface TemplateAreaConverter {

	TemplateAreaConverter INSTANCE = Mappers.getMapper(TemplateAreaConverter.class);

	/**
	 * PO 转 PageVO
	 * @param templateArea 模板文件文本域表
	 * @return TemplateAreaPageVO 模板文件文本域表PageVO
	 */
	TemplateAreaPageVO poToPageVo(TemplateArea templateArea);

}
