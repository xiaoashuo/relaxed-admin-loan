package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.vo.CertificatePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 签章证书表模型转换器
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Mapper
public interface CertificateConverter {

	CertificateConverter INSTANCE = Mappers.getMapper(CertificateConverter.class);

	/**
	 * PO 转 PageVO
	 * @param certificate 签章证书表
	 * @return CertificatePageVO 签章证书表PageVO
	 */
	CertificatePageVO poToPageVo(Certificate certificate);

}
