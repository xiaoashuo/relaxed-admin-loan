package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.loan.converter.TrustPlanConverter;
import com.relaxed.boot.loan.model.entity.TrustPlan;
import com.relaxed.boot.loan.model.qo.TrustPlanQO;
import com.relaxed.boot.loan.model.vo.TrustPlanPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 信托计划配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface TrustPlanMapper extends ExtendMapper<TrustPlan> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<TrustPlanPageVO> VO分页数据
	 */
	default PageResult<TrustPlanPageVO> queryPage(PageParam pageParam, TrustPlanQO qo) {
		IPage<TrustPlan> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<TrustPlan> wrapper = WrappersX.lambdaQueryX(TrustPlan.class);
		this.selectPage(page, wrapper);
		IPage<TrustPlanPageVO> voPage = page.convert(TrustPlanConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}