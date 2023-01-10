package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.vo.OrderPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Mapper
public interface OrderConverter {

	OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

	/**
	 * PO 转 PageVO
	 * @param order 
	 * @return OrderPageVO PageVO
	 */
	OrderPageVO poToPageVo(Order order);

}
