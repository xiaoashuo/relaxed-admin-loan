package com.relaxed.boot.risk.model.converter;

import com.relaxed.boot.risk.model.dto.RiskRuleHistoryDTO;
import com.relaxed.boot.risk.model.entity.RiskRuleHistory;
import com.relaxed.boot.risk.model.vo.RiskRuleHistoryVO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换器
 * </p>
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.317
 */
@Mapper
public interface RiskRuleHistoryConverter {

	RiskRuleHistoryConverter INSTANCE = Mappers.getMapper(RiskRuleHistoryConverter.class);

	/**
	 * po -> vo
	 * @param ruleHistory {@link RiskRuleHistory}
	 * @return {@link RiskRuleHistoryVO}
	 */
	RiskRuleHistoryVO poToVo(RiskRuleHistory ruleHistory);

	/**
	 * dto -> po
	 * @param ruleHistoryDTO {@link RiskRuleHistoryDTO}
	 * @return {@link RiskRuleHistory}
	 */
	RiskRuleHistory dtoToPo(RiskRuleHistoryDTO ruleHistoryDTO);

	/**
	 * po -> vos
	 * @param ruleHistoryProperties {@link List<RiskRuleHistory>}
	 * @return {@link List<RiskRuleHistoryVO>}
	 */
	List<RiskRuleHistoryVO> poToVOs(List<RiskRuleHistory> ruleHistoryProperties);

}
