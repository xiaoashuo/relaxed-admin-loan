package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.OauthClientDetails;
import com.relaxed.boot.common.system.model.vo.OauthClientDetailsPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 接入客户端信息模型转换器
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Mapper
public interface OauthClientDetailsConverter {

	OauthClientDetailsConverter INSTANCE = Mappers.getMapper(OauthClientDetailsConverter.class);

	/**
	 * PO 转 PageVO
	 * @param oauthClientDetails 接入客户端信息
	 * @return OauthClientDetailsPageVO 接入客户端信息PageVO
	 */
	OauthClientDetailsPageVO poToPageVo(OauthClientDetails oauthClientDetails);

}
