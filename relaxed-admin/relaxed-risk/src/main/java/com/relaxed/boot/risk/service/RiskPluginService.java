package com.relaxed.boot.risk.service;

import com.relaxed.boot.risk.model.entity.RiskPlugin;
import com.relaxed.boot.risk.model.qo.RiskPluginQO;
import com.relaxed.boot.risk.model.vo.RiskPluginVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * <p>
 * 业务层
 * </p>
 *
 * @author Yakir
 * @since 2021-09-28T13:43:11.834
 */
public interface RiskPluginService extends ExtendService<RiskPlugin> {

	/**
	 * 分页查询
	 * @param pageParam {@link PageParam}
	 * @param pluginQO {@link RiskPluginQO}
	 * @return {@link PageResult< RiskPluginVO >}
	 */
	PageResult<RiskPluginVO> selectByPage(PageParam pageParam, RiskPluginQO pluginQO);

}
