package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.OrderGuarantor;
import com.relaxed.boot.loan.model.vo.OrderGuarantorPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 担保机构模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface OrderGuarantorConverter {

	OrderGuarantorConverter INSTANCE = Mappers.getMapper(OrderGuarantorConverter.class);

	/**
	 * PO 转 PageVO
	 * @param orderGuarantor 担保机构
	 * @return OrderGuarantorPageVO 担保机构PageVO
	 */
	OrderGuarantorPageVO poToPageVo(OrderGuarantor orderGuarantor);

}
