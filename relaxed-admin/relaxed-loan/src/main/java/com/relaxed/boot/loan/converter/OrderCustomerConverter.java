package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.OrderCustomer;
import com.relaxed.boot.loan.model.vo.OrderCustomerPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface OrderCustomerConverter {

	OrderCustomerConverter INSTANCE = Mappers.getMapper(OrderCustomerConverter.class);

	/**
	 * PO 转 PageVO
	 * @param orderCustomer 
	 * @return OrderCustomerPageVO PageVO
	 */
	OrderCustomerPageVO poToPageVo(OrderCustomer orderCustomer);

}
