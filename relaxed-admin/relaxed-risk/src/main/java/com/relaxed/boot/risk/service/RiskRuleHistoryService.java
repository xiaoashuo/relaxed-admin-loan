package com.relaxed.boot.risk.service;

import com.relaxed.boot.risk.model.entity.RiskRuleHistory;
import com.relaxed.boot.risk.model.qo.RiskRuleHistoryQO;
import com.relaxed.boot.risk.model.vo.RiskRuleHistoryVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * <p>
 * 业务层
 * </p>
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.317
 */
public interface RiskRuleHistoryService extends ExtendService<RiskRuleHistory> {

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam}
	 * @param ruleHistoryQO {@link RiskRuleHistoryQO}
	 * @return {@link PageResult<RiskRuleHistoryVO>}
	 */
	PageResult<RiskRuleHistoryVO> selectByPage(PageParam pageParam, RiskRuleHistoryQO ruleHistoryQO);

}
