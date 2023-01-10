package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.vo.AuditPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 审核表模型转换器
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Mapper
public interface AuditConverter {

	AuditConverter INSTANCE = Mappers.getMapper(AuditConverter.class);

	/**
	 * PO 转 PageVO
	 * @param audit 审核表
	 * @return AuditPageVO 审核表PageVO
	 */
	AuditPageVO poToPageVo(Audit audit);

}
