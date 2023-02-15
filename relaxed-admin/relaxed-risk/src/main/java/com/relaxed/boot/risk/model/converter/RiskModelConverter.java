package com.relaxed.boot.risk.model.converter;

import com.relaxed.boot.risk.model.dto.RiskModelDTO;
import com.relaxed.boot.risk.model.entity.RiskModel;
import com.relaxed.boot.risk.model.vo.RiskModelVO;

import io.swagger.v3.core.converter.ModelConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换器
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T09:04:20.388
 */
@Mapper
public interface RiskModelConverter {

	RiskModelConverter INSTANCE = Mappers.getMapper(RiskModelConverter.class);

	/**
	 * po -> vo
	 * @param model {@link RiskModel}
	 * @return {@link RiskModelVO}
	 */
	RiskModelVO poToVo(RiskModel model);

	/**
	 * dto -> po
	 * @param modelDTO {@link RiskModelDTO}
	 * @return {@link RiskModel}
	 */
	RiskModel dtoToPo(RiskModelDTO modelDTO);

	/**
	 * po -> vos
	 * @param modelProperties {@link List<RiskModel>}
	 * @return {@link List<RiskModelVO>}
	 */
	List<RiskModelVO> poToVOs(List<RiskModel> modelProperties);

}
