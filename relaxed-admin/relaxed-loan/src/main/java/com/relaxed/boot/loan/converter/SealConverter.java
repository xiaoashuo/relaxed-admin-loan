package com.relaxed.boot.loan.converter;

import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.vo.SealPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 签章图片表模型转换器
 *
 * @author yakir 2022-12-15 10:25:25
 */
@Mapper
public interface SealConverter {

	SealConverter INSTANCE = Mappers.getMapper(SealConverter.class);

	/**
	 * PO 转 PageVO
	 * @param seal 签章图片表
	 * @return SealPageVO 签章图片表PageVO
	 */
	SealPageVO poToPageVo(Seal seal);

	/**
	 * po->vos
	 * @param seals
	 * @return
	 */
	List<SealPageVO> poToPageVos(List<Seal> seals);

}
