package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.StampRecord;
import com.relaxed.boot.loan.model.vo.StampRecordPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 签章记录模型转换器
 *
 * @author yakir 2023-01-29 09:31:45
 */
@Mapper
public interface StampRecordConverter {

	StampRecordConverter INSTANCE = Mappers.getMapper(StampRecordConverter.class);

	/**
	 * PO 转 PageVO
	 * @param stampRecord 签章记录
	 * @return StampRecordPageVO 签章记录PageVO
	 */
	StampRecordPageVO poToPageVo(StampRecord stampRecord);

}
