package com.relaxed.boot.risk.service;

import com.relaxed.boot.risk.model.entity.RiskField;
import com.relaxed.boot.risk.model.qo.RiskFieldQO;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * <p>
 * 业务层
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T12:14:38.328
 */
public interface RiskFieldService extends ExtendService<RiskField> {

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam}
	 * @param fieldQO {@link RiskFieldQO}
	 * @return {@link PageResult<RiskFieldVO>}
	 */
	PageResult<RiskFieldVO> selectByPage(PageParam pageParam, RiskFieldQO fieldQO);

	/**
	 * 根据modelId查询字段列表
	 * @author yakir
	 * @date 2021/8/29 12:52
	 * @param modelId
	 * @return java.util.List<com.relaxed.common.risk.model.entity.Field>
	 */
	List<RiskFieldVO> listByModelId(Long modelId);

	/**
	 * 查询单字段
	 * @author yakir
	 * @date 2021/9/19 17:24
	 * @param modelId
	 * @param fieldName
	 * @return com.relaxed.common.risk.model.entity.Field
	 */
	RiskField selectOne(Long modelId, String fieldName);


}
