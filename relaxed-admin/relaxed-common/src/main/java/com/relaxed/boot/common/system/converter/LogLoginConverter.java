package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.LogLogin;
import com.relaxed.boot.common.system.model.vo.LogLoginPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 登陆日志模型转换器
 *
 * @author Yakir 2022-11-22 14:02:00
 */
@Mapper
public interface LogLoginConverter {

	LogLoginConverter INSTANCE = Mappers.getMapper(LogLoginConverter.class);

	/**
	 * PO 转 PageVO
	 * @param logLogin 登陆日志
	 * @return LogLoginPageVO 登陆日志PageVO
	 */
	LogLoginPageVO poToPageVo(LogLogin logLogin);

}
