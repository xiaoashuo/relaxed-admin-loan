package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.OrderBankCardDetail;
import com.relaxed.boot.loan.model.vo.OrderBankCardDetailPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单银行卡放款详情表模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface OrderBankCardDetailConverter {

	OrderBankCardDetailConverter INSTANCE = Mappers.getMapper(OrderBankCardDetailConverter.class);

	/**
	 * PO 转 PageVO
	 * @param orderBankCardDetail 订单银行卡放款详情表
	 * @return OrderBankCardDetailPageVO 订单银行卡放款详情表PageVO
	 */
	OrderBankCardDetailPageVO poToPageVo(OrderBankCardDetail orderBankCardDetail);

}
