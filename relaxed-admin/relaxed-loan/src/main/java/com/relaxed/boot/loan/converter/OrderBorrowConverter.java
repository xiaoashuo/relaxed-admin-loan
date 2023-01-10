package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.OrderBorrow;
import com.relaxed.boot.loan.model.vo.OrderBorrowPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 共同借款人模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface OrderBorrowConverter {

	OrderBorrowConverter INSTANCE = Mappers.getMapper(OrderBorrowConverter.class);

	/**
	 * PO 转 PageVO
	 * @param orderBorrow 共同借款人
	 * @return OrderBorrowPageVO 共同借款人PageVO
	 */
	OrderBorrowPageVO poToPageVo(OrderBorrow orderBorrow);

}
