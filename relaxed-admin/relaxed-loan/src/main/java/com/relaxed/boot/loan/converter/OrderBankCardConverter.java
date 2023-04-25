package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.OrderBankCard;
import com.relaxed.boot.loan.model.vo.OrderBankCardPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface OrderBankCardConverter {

	OrderBankCardConverter INSTANCE = Mappers.getMapper(OrderBankCardConverter.class);

	/**
	 * PO 转 PageVO
	 * @param orderBankCard
	 * @return OrderBankCardPageVO PageVO
	 */
	OrderBankCardPageVO poToPageVo(OrderBankCard orderBankCard);

}
