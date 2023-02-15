package com.relaxed.boot.risk.model.converter;

import com.relaxed.boot.risk.model.dto.RiskPreItemDTO;
import com.relaxed.boot.risk.model.entity.RiskPreItem;
import com.relaxed.boot.risk.model.vo.RiskPreItemVO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换器
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T13:57:50.664
 */
@Mapper
public interface RiskPreItemConverter {

	RiskPreItemConverter INSTANCE = Mappers.getMapper(RiskPreItemConverter.class);

	/**
	 * po -> vo
	 * @param preItem {@link RiskPreItem}
	 * @return {@link RiskPreItemVO}
	 */
	RiskPreItemVO poToVo(RiskPreItem preItem);

	/**
	 * dto -> po
	 * @param preItemDTO {@link RiskPreItemDTO}
	 * @return {@link RiskPreItem}
	 */
	RiskPreItem dtoToPo(RiskPreItemDTO preItemDTO);

	/**
	 * po -> vos
	 * @param preItemProperties {@link List< RiskPreItem >}
	 * @return {@link List<RiskPreItemVO>}
	 */
	List<RiskPreItemVO> poToVOs(List<RiskPreItem> preItemProperties);

}
