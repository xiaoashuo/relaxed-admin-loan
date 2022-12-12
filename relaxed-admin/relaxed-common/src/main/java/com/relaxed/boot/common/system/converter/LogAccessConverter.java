package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.LogAccess;
import com.relaxed.boot.common.system.model.vo.LogAccessPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 访问日志模型转换器
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Mapper
public interface LogAccessConverter {

	LogAccessConverter INSTANCE = Mappers.getMapper(LogAccessConverter.class);

	/**
	 * PO 转 PageVO
	 * @param logAccess 访问日志
	 * @return LogAccessPageVO 访问日志PageVO
	 */
	LogAccessPageVO poToPageVo(LogAccess logAccess);

}
