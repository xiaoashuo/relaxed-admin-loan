package com.relaxed.boot.risk.service;

import com.relaxed.boot.risk.model.entity.RiskModel;
import com.relaxed.boot.risk.model.qo.RiskModelQO;
import com.relaxed.boot.risk.model.vo.RiskModelVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * @author Yakir
 * @Topic ModelService
 * @Description
 * @date 2021/8/29 8:50
 * @Version 1.0
 */
public interface RiskModelService extends ExtendService<RiskModel> {

	/**
	 * 查询列表
	 * @author yakir
	 * @date 2021/8/29 9:10
	 * @param pageParam
	 * @param modelQO
	 * @return com.relaxed.common.model.domain.PageResult<com.relaxed.common.risk.model.vo.ModelVO>
	 */
	PageResult<RiskModelVO> selectByPage(PageParam pageParam, RiskModelQO modelQO);

	/**
	 * 根据状态查询列表
	 * @author yakir
	 * @date 2021/8/29 10:14
	 * @param status
	 * @return java.util.List<com.relaxed.common.risk.model.vo.ModelVO>
	 */
	List<RiskModelVO> listByStatus(Integer status);

	/**
	 * 根据guid查询model
	 * @author yakir
	 * @date 2021/8/29 10:52
	 * @param guid
	 * @return com.relaxed.common.risk.model.vo.ModelVO
	 */
	RiskModelVO getByGuid(String guid);

	/**
	 * 根据模型名称查询模型
	 * @author yakir
	 * @date 2021/9/12 17:04
	 * @param modelName
	 * @return com.relaxed.common.risk.model.entity.Model
	 */
	RiskModel getByModelName(String modelName);

}
