package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.vo.LoanPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 借款合同表模型转换器
 *
 * @author yakir 2023-01-10 10:32:19
 */
@Mapper
public interface LoanConverter {

	LoanConverter INSTANCE = Mappers.getMapper(LoanConverter.class);

	/**
	 * PO 转 PageVO
	 * @param loan 借款合同表
	 * @return LoanPageVO 借款合同表PageVO
	 */
	LoanPageVO poToPageVo(Loan loan);

}
