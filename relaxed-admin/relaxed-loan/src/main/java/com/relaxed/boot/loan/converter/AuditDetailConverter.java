package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.AuditDetail;
import com.relaxed.boot.loan.model.vo.AuditDetailPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 审核拒绝原因表模型转换器
 *
 * @author yakir 2023-01-10 10:32:31
 */
@Mapper
public interface AuditDetailConverter {

	AuditDetailConverter INSTANCE = Mappers.getMapper(AuditDetailConverter.class);

	/**
	 * PO 转 PageVO
	 * @param auditDetail 审核拒绝原因表
	 * @return AuditDetailPageVO 审核拒绝原因表PageVO
	 */
	AuditDetailPageVO poToPageVo(AuditDetail auditDetail);

}
