package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Bill;
import com.relaxed.boot.loan.model.vo.BillPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Mapper
public interface BillConverter {

	BillConverter INSTANCE = Mappers.getMapper(BillConverter.class);

	/**
	 * PO 转 PageVO
	 * @param bill 
	 * @return BillPageVO PageVO
	 */
	BillPageVO poToPageVo(Bill bill);

}
