package com.relaxed.boot.risk.model.converter;

import com.relaxed.boot.risk.model.dto.RiskPluginDTO;
import com.relaxed.boot.risk.model.entity.RiskPlugin;
import com.relaxed.boot.risk.model.vo.RiskPluginVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 转换器
 * </p>
 *
 * @author Yakir
 * @since 2021-09-28T13:43:11.834
 */
@Mapper
public interface RiskPluginConverter {

	RiskPluginConverter INSTANCE = Mappers.getMapper(RiskPluginConverter.class);

	/**
	 * po -> vo
	 * @param plugin {@link RiskPlugin}
	 * @return {@link RiskPluginVO}
	 */
	RiskPluginVO poToVo(RiskPlugin plugin);

	/**
	 * dto -> po
	 * @param pluginDTO {@link RiskPluginDTO}
	 * @return {@link RiskPlugin}
	 */
	RiskPlugin dtoToPo(RiskPluginDTO pluginDTO);

	/**
	 * po -> vos
	 * @param pluginProperties {@link List<RiskPlugin>}
	 * @return {@link List<RiskPluginVO>}
	 */
	List<RiskPluginVO> poToVOs(List<RiskPlugin> pluginProperties);

}
