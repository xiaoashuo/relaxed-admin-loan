package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.OrderCollateral;
import com.relaxed.boot.loan.model.vo.OrderCollateralPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Mapper
public interface OrderCollateralConverter {

	OrderCollateralConverter INSTANCE = Mappers.getMapper(OrderCollateralConverter.class);

	/**
	 * PO 转 PageVO
	 * @param orderCollateral 
	 * @return OrderCollateralPageVO PageVO
	 */
	OrderCollateralPageVO poToPageVo(OrderCollateral orderCollateral);

}
