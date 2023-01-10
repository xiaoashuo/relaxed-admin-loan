package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Trade;
import com.relaxed.boot.loan.model.vo.TradePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Mapper
public interface TradeConverter {

	TradeConverter INSTANCE = Mappers.getMapper(TradeConverter.class);

	/**
	 * PO 转 PageVO
	 * @param trade 
	 * @return TradePageVO PageVO
	 */
	TradePageVO poToPageVo(Trade trade);

}
