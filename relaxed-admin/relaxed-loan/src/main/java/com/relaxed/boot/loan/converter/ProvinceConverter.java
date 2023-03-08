package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Province;
import com.relaxed.boot.loan.model.vo.ProvincePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 省市区码表模型转换器
 *
 * @author Yakir 2023-03-08 10:44:09
 */
@Mapper
public interface ProvinceConverter {

	ProvinceConverter INSTANCE = Mappers.getMapper(ProvinceConverter.class);

	/**
	 * PO 转 PageVO
	 * @param province 省市区码表
	 * @return ProvincePageVO 省市区码表PageVO
	 */
	ProvincePageVO poToPageVo(Province province);

}
