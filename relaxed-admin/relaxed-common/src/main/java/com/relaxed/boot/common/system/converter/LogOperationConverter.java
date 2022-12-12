package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.LogOperation;
import com.relaxed.boot.common.system.model.vo.LogOperationPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 操作日志模型转换器
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Mapper
public interface LogOperationConverter {

	LogOperationConverter INSTANCE = Mappers.getMapper(LogOperationConverter.class);

	/**
	 * PO 转 PageVO
	 * @param logOperation 操作日志
	 * @return LogOperationPageVO 操作日志PageVO
	 */
	LogOperationPageVO poToPageVo(LogOperation logOperation);

}
