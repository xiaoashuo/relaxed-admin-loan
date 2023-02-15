package com.relaxed.boot.risk.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.model.entity.RiskActivation;

import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T18:48:19.435
 */
public interface RiskActivationMapper extends ExtendMapper<RiskActivation> {

	/**
	 * 查询列表
	 * @author yakir
	 * @date 2021/8/31 11:00
	 * @param modelId
	 * @return java.util.List<com.relaxed.common.risk.model.entity.Activation>
	 */
	default List<RiskActivation> listByModelId(Long modelId) {
		return this.selectList(Wrappers.lambdaQuery(RiskActivation.class).eq(RiskActivation::getModelId, modelId));
	}

}
