package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.dto.SysDictItemDTO;
import com.relaxed.boot.common.system.model.entity.SysDictItem;
import com.relaxed.boot.common.system.model.vo.DictItemVO;
import com.relaxed.boot.common.system.model.vo.SysDictItemPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典项模型转换器
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Mapper
public interface SysDictItemConverter {

	SysDictItemConverter INSTANCE = Mappers.getMapper(SysDictItemConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysDictItem 字典项
	 * @return SysDictItemPageVO 字典项PageVO
	 */
	SysDictItemPageVO poToPageVo(SysDictItem sysDictItem);

	/**
	 * 字典项实体 转 VO
	 * @param sysDictItem 字典项
	 * @return 字典项VO
	 */
	DictItemVO poToItemVo(SysDictItem sysDictItem);

	/**
	 * 字典项传输对象转实体
	 * @param sysDictItemDTO 传输对象
	 * @return SysDictItem
	 */
	SysDictItem dtoToPo(SysDictItemDTO sysDictItemDTO);

}
