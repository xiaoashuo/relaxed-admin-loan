package com.relaxed.boot.risk.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.model.entity.RiskField;

import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T12:14:38.328
 */
public interface RiskFieldMapper extends ExtendMapper<RiskField> {

	/**
	 * 根据model id查询字段列表
	 * @author yakir
	 * @date 2021/8/29 12:53
	 * @param modelId
	 * @return java.util.List<com.relaxed.common.risk.model.entity.Field>
	 */
	default List<RiskField> listByModelId(Long modelId) {
		return this.selectList(Wrappers.lambdaQuery(RiskField.class).eq(RiskField::getModelId, modelId));
	};

	/**
	 * 查询单字段
	 * @author yakir
	 * @date 2021/9/19 17:24
	 * @param modelId
	 * @param fieldName
	 * @return com.relaxed.common.risk.model.entity.Field
	 */
	default RiskField selectOne(Long modelId, String fieldName) {
		return this.selectOne(Wrappers.lambdaQuery(RiskField.class).eq(RiskField::getModelId, modelId)
				.eq(RiskField::getFieldName, fieldName));
	}

}
