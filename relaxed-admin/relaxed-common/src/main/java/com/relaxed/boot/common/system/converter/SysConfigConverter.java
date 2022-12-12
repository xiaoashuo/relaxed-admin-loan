package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.SysConfig;
import com.relaxed.boot.common.system.model.vo.SysConfigPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 基础配置模型转换器
 *
 * @author yakir 2022-11-15 10:24:40
 */
@Mapper
public interface SysConfigConverter {

	SysConfigConverter INSTANCE = Mappers.getMapper(SysConfigConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysConfig 基础配置
	 * @return SysConfigPageVO 基础配置PageVO
	 */
	SysConfigPageVO poToPageVo(SysConfig sysConfig);

}
