package com.relaxed.boot.risk.model.converter;

import com.relaxed.boot.risk.model.dto.RiskFieldDTO;
import com.relaxed.boot.risk.model.entity.RiskField;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换器
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T12:14:38.328
 */
@Mapper
public interface RiskFieldConverter {

	RiskFieldConverter INSTANCE = Mappers.getMapper(RiskFieldConverter.class);

	/**
	 * po -> vo
	 * @param field {@link RiskField}
	 * @return {@link RiskFieldVO}
	 */
	RiskFieldVO poToVo(RiskField field);

	/**
	 * dto -> po
	 * @param fieldDTO {@link RiskFieldDTO}
	 * @return {@link RiskField}
	 */
	RiskField dtoToPo(RiskFieldDTO fieldDTO);

	/**
	 * po -> vos
	 * @param fieldProperties {@link List<RiskField>}
	 * @return {@link List<RiskFieldVO>}
	 */
	List<RiskFieldVO> poToVOs(List<RiskField> fieldProperties);

}
