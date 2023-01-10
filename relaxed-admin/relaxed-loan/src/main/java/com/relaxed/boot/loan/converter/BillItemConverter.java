package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.BillItem;
import com.relaxed.boot.loan.model.vo.BillItemPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Mapper
public interface BillItemConverter {

	BillItemConverter INSTANCE = Mappers.getMapper(BillItemConverter.class);

	/**
	 * PO 转 PageVO
	 * @param billItem 
	 * @return BillItemPageVO PageVO
	 */
	BillItemPageVO poToPageVo(BillItem billItem);

}
