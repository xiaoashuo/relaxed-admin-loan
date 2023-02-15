package com.relaxed.boot.risk.model.converter;

import com.relaxed.boot.risk.model.dto.RiskRuleDTO;
import com.relaxed.boot.risk.model.entity.RiskRule;
import com.relaxed.boot.risk.model.vo.RiskRuleVO;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换器
 * </p>
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.273
 */
@Mapper
public interface RiskRuleConverter {

	RiskRuleConverter INSTANCE = Mappers.getMapper(RiskRuleConverter.class);

	/**
	 * po -> vo
	 * @param rule {@link RiskRule}
	 * @return {@link RiskRuleVO}
	 */
	RiskRuleVO poToVo(RiskRule rule);

	/**
	 * dto -> po
	 * @param ruleDTO {@link RiskRuleDTO}
	 * @return {@link RiskRule}
	 */
	RiskRule dtoToPo(RiskRuleDTO ruleDTO);

	/**
	 * po -> vos
	 * @param ruleProperties {@link List<RiskRule>}
	 * @return {@link List<RiskRuleVO>}
	 */
	List<RiskRuleVO> poToVOs(List<RiskRule> ruleProperties);

}
