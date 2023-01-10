package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.BankCode;
import com.relaxed.boot.loan.model.vo.BankCodePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 银行编码表模型转换器
 *
 * @author yakir 2023-01-10 10:13:02
 */
@Mapper
public interface BankCodeConverter {

	BankCodeConverter INSTANCE = Mappers.getMapper(BankCodeConverter.class);

	/**
	 * PO 转 PageVO
	 * @param bankCode 银行编码表
	 * @return BankCodePageVO 银行编码表PageVO
	 */
	BankCodePageVO poToPageVo(BankCode bankCode);

}
