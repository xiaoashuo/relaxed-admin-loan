package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Partner;
import com.relaxed.boot.loan.model.vo.PartnerPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 合作方模型转换器
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Mapper
public interface PartnerConverter {

	PartnerConverter INSTANCE = Mappers.getMapper(PartnerConverter.class);

	/**
	 * PO 转 PageVO
	 * @param partner 合作方
	 * @return PartnerPageVO 合作方PageVO
	 */
	PartnerPageVO poToPageVo(Partner partner);

}
