package com.relaxed.boot.risk.model.converter;

import com.relaxed.boot.risk.model.dto.RiskActivationDTO;
import com.relaxed.boot.risk.model.entity.RiskActivation;
import com.relaxed.boot.risk.model.vo.RiskActivationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sun.rmi.server.Activation;

import java.util.List;

/**
 * <p>
 * 转换器
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T18:48:19.435
 */
@Mapper
public interface RiskActivationConverter {

	RiskActivationConverter INSTANCE = Mappers.getMapper(RiskActivationConverter.class);

	/**
	 * po -> vo
	 * @param activation {@link RiskActivation}
	 * @return {@link RiskActivationVO}
	 */
	RiskActivationVO poToVo(RiskActivation activation);

	/**
	 * dto -> po
	 * @param activationDTO {@link RiskActivationDTO}
	 * @return {@link RiskActivation}
	 */
	RiskActivation dtoToPo(RiskActivationDTO activationDTO);

	/**
	 * po -> dto
	 * @date 2023/2/15 14:38
	 * @param activation
	 * @return com.relaxed.boot.risk.model.dto.RiskActivationDTO
	 */
	RiskActivationDTO poToDto(RiskActivation activation);

	/**
	 * po -> vos
	 * @param activationProperties {@link List<RiskActivation>}
	 * @return {@link List<RiskActivationVO>}
	 */
	List<RiskActivationVO> poToVOs(List<RiskActivation> activationProperties);

}
