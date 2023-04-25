package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.BillItemFiller;
import com.relaxed.boot.loan.model.vo.BillItemFillerPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface BillItemFillerConverter {

	BillItemFillerConverter INSTANCE = Mappers.getMapper(BillItemFillerConverter.class);

	/**
	 * PO 转 PageVO
	 * @param billItemFiller
	 * @return BillItemFillerPageVO PageVO
	 */
	BillItemFillerPageVO poToPageVo(BillItemFiller billItemFiller);

	List<BillItemFillerPageVO> poToPageVos(List<BillItemFiller> billItemFillers);

}
