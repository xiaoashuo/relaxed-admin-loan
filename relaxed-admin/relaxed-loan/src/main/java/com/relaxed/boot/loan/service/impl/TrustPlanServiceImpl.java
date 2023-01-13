package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.loan.model.entity.TrustPlan;
import com.relaxed.boot.loan.model.vo.TrustPlanPageVO;
import com.relaxed.boot.loan.model.qo.TrustPlanQO;
import com.relaxed.boot.loan.mapper.TrustPlanMapper;
import com.relaxed.boot.loan.service.TrustPlanService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 信托计划配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class TrustPlanServiceImpl extends ExtendServiceImpl<TrustPlanMapper, TrustPlan> implements TrustPlanService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<TrustPlanPageVO> 分页数据
	 */
	@Override
	public PageResult<TrustPlanPageVO> queryPage(PageParam pageParam, TrustPlanQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public List<SelectData> querySelectData() {
		List<TrustPlan> trustPlanList = list();
		List<SelectData> selectDataList = trustPlanList.stream().map(e -> {
			SelectData<Void> selectData = new SelectData<>();
			selectData.setLabel(e.getTrustPlanName());
			selectData.setValue(e.getTrustPlanNo());
			return selectData;
		}).collect(Collectors.toList());
		return selectDataList;
	}

	@Override
	public TrustPlan getByTrustPlanCode(Integer trustPlanCode) {
		return getOne(Wrappers.lambdaQuery(TrustPlan.class).eq(TrustPlan::getTrustPlanNo, trustPlanCode));
	}
}
