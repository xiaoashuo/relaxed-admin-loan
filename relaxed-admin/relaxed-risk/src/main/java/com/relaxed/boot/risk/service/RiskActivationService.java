package com.relaxed.boot.risk.service;

import com.relaxed.boot.risk.model.entity.RiskActivation;
import com.relaxed.boot.risk.model.qo.RiskActivationQO;
import com.relaxed.boot.risk.model.vo.RiskActivationVO;
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
 * @since 2021-08-29T18:48:19.435
 */
public interface RiskActivationService extends ExtendService<RiskActivation> {

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam}
	 * @param activationQO {@link RiskActivationQO}
	 * @return {@link PageResult<RiskActivationVO>}
	 */
	PageResult<RiskActivationVO> selectByPage(PageParam pageParam, RiskActivationQO activationQO);

	/**
	 * 根据modelID查询
	 * @author yakir
	 * @date 2021/8/31 10:51
	 * @param modelId
	 * @return java.util.List<com.relaxed.common.risk.model.vo.ActivationVO>
	 */
	List<RiskActivationVO> listByModelId(Long modelId);

}
