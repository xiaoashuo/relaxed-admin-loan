package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.SysDict;
import com.relaxed.boot.common.system.model.vo.SysDictPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典表模型转换器
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Mapper
public interface SysDictConverter {

	SysDictConverter INSTANCE = Mappers.getMapper(SysDictConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysDict 字典表
	 * @return SysDictPageVO 字典表PageVO
	 */
	SysDictPageVO poToPageVo(SysDict sysDict);

}
