package com.relaxed.boot.risk.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.model.entity.RiskPreItem;

import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T13:57:50.664
 */
public interface RiskPreItemMapper extends ExtendMapper<RiskPreItem> {

	/**
	 * 根据模型id查询预处理项
	 * @author yakir
	 * @date 2021/8/29 14:16
	 * @param modelId
	 * @return java.util.List<com.relaxed.common.risk.model.entity.PreItem>
	 */
	default List<RiskPreItem> getByModelId(Long modelId) {
		return this.selectList(Wrappers.lambdaQuery(RiskPreItem.class).eq(RiskPreItem::getModelId, modelId));
	}

	/**
	 * 查询预处理字段 根据模型id 与 目标字段描述
	 * @author yakir
	 * @date 2021/9/12 17:32
	 * @param modelId
	 * @param destField
	 * @return com.relaxed.common.risk.model.entity.PreItem
	 */
	default RiskPreItem selectOne(Long modelId, String destField) {
		return this.selectOne(Wrappers.lambdaQuery(RiskPreItem.class).eq(RiskPreItem::getModelId, modelId)
				.eq(RiskPreItem::getDestField, destField));
	}

	/**
	 * 查询预处理字段 根据model id 与id
	 * @author yakir
	 * @date 2021/9/12 18:09
	 * @param modelId
	 * @param id
	 * @return com.relaxed.common.risk.model.entity.PreItem
	 */
	default RiskPreItem selectOne(Long modelId, Long id) {
		return this
				.selectOne(Wrappers.lambdaQuery(RiskPreItem.class).eq(RiskPreItem::getModelId, modelId).eq(RiskPreItem::getId, id));
	}

	/**
	 * 查询一个根据model id
	 * @author yakir
	 * @date 2021/9/12 17:59
	 * @param modelId
	 * @return com.relaxed.common.risk.model.entity.PreItem
	 */
	default RiskPreItem selectOne(Long modelId) {
		return this.selectOne(Wrappers.lambdaQuery(RiskPreItem.class).eq(RiskPreItem::getModelId, modelId));
	}

}
