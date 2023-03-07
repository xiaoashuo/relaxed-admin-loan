package com.relaxed.boot.risk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.mapper.RiskRuleMapper;
import com.relaxed.boot.risk.model.converter.RiskRuleConverter;
import com.relaxed.boot.risk.model.entity.RiskRule;
import com.relaxed.boot.risk.model.qo.RiskRuleQO;
import com.relaxed.boot.risk.model.vo.RiskRuleVO;
import com.relaxed.boot.risk.service.RiskRuleService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.extend.mybatis.plus.toolkit.PageUtil;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务层实现
 * </p>
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.273
 */
@RequiredArgsConstructor
@Service
public class RiskRuleServiceImpl extends ExtendServiceImpl<RiskRuleMapper, RiskRule> implements RiskRuleService {

	@Override
	public PageResult<RiskRuleVO> selectByPage(PageParam pageParam, RiskRuleQO ruleQO) {
		IPage<RiskRule> page = PageUtil.prodPage(pageParam);
		LambdaQueryWrapperX<RiskRule> wrapper = WrappersX.lambdaQueryX(RiskRule.class);

		wrapper.eq(ObjectUtil.isNotNull(ruleQO.getId()),
				RiskRule::getId, ruleQO.getId())
				.likeIfPresent(RiskRule::getLabel,ruleQO.getLabel())
				.orderByDesc(RiskRule::getId)
				;
		this.baseMapper.selectPage(page, wrapper);
		IPage<RiskRuleVO> voPage = page.convert(RiskRuleConverter.INSTANCE::poToVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	@Override
	public List<RiskRuleVO> listByActivationId(Long activationId) {
		List<RiskRule> list = baseMapper.listByActivationId(activationId);
		return RiskRuleConverter.INSTANCE.poToVOs(list);
	}

	@Override
	public RiskRule getByName(String ruleName) {
		return baseMapper.selectOne(ruleName);
	}

}
