package com.relaxed.boot.risk.service;

import com.relaxed.boot.risk.model.entity.RiskRule;
import com.relaxed.boot.risk.model.qo.RiskRuleQO;
import com.relaxed.boot.risk.model.vo.RiskRuleVO;
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
 * @since 2021-08-31T11:30:23.273
 */
public interface RiskRuleService extends ExtendService<RiskRule> {

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam}
	 * @param ruleQO {@link RiskRuleQO}
	 * @return {@link PageResult<RiskRuleVO>}
	 */
	PageResult<RiskRuleVO> selectByPage(PageParam pageParam, RiskRuleQO ruleQO);

	/**
	 * 查询列表
	 * @author yakir
	 * @date 2021/8/31 11:46
	 * @param activationId
	 * @return java.util.List<com.relaxed.common.risk.model.vo.RuleVO>
	 */
	List<RiskRuleVO> listByActivationId(Long activationId);

	/**
	 * 根据规则名称查询规则
	 * @author yakir
	 * @date 2021/9/24 14:38
	 * @param ruleName
	 * @return com.relaxed.common.risk.model.entity.Rule
	 */
	RiskRule getByName(String ruleName);

}
