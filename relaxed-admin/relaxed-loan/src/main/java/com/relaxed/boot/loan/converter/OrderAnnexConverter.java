package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.OrderAnnex;
import com.relaxed.boot.loan.model.vo.OrderAnnexPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface OrderAnnexConverter {

	OrderAnnexConverter INSTANCE = Mappers.getMapper(OrderAnnexConverter.class);

	/**
	 * PO 转 PageVO
	 * @param orderAnnex 
	 * @return OrderAnnexPageVO PageVO
	 */
	OrderAnnexPageVO poToPageVo(OrderAnnex orderAnnex);

}
