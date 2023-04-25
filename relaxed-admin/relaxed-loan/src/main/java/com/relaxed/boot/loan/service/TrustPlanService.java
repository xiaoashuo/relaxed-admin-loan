package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.TrustPlan;
import com.relaxed.boot.loan.model.vo.TrustPlanPageVO;
import com.relaxed.boot.loan.model.qo.TrustPlanQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 信托计划配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface TrustPlanService extends ExtendService<TrustPlan> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;TrustPlanPageVO&gt; 分页数据
	 */
	PageResult<TrustPlanPageVO> queryPage(PageParam pageParam, TrustPlanQO qo);

	List<SelectData> querySelectData();

	TrustPlan getByTrustPlanCode(Integer trustPlanCode);

}
