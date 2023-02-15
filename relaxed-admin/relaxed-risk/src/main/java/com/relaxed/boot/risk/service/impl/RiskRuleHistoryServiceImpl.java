package com.relaxed.boot.risk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.mapper.RiskRuleHistoryMapper;
import com.relaxed.boot.risk.model.converter.RiskRuleHistoryConverter;
import com.relaxed.boot.risk.model.entity.RiskRuleHistory;
import com.relaxed.boot.risk.model.qo.RiskRuleHistoryQO;
import com.relaxed.boot.risk.model.vo.RiskRuleHistoryVO;
import com.relaxed.boot.risk.service.RiskRuleHistoryService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.extend.mybatis.plus.toolkit.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务层实现
 * </p>
 *
 * @author Yakir
 * @since 2021-08-31T11:30:23.317
 */
@RequiredArgsConstructor
@Service
public class RiskRuleHistoryServiceImpl extends ExtendServiceImpl<RiskRuleHistoryMapper, RiskRuleHistory>
		implements RiskRuleHistoryService {

	@Override
	public PageResult<RiskRuleHistoryVO> selectByPage(PageParam pageParam, RiskRuleHistoryQO ruleHistoryQO) {
		IPage<RiskRuleHistory> page = PageUtil.prodPage(pageParam);
		LambdaQueryWrapper<RiskRuleHistory> wrapper = Wrappers.lambdaQuery(RiskRuleHistory.class)
				.eq(ObjectUtil.isNotNull(ruleHistoryQO.getId()), RiskRuleHistory::getId, ruleHistoryQO.getId());
		this.baseMapper.selectPage(page, wrapper);
		IPage<RiskRuleHistoryVO> voPage = page.convert(RiskRuleHistoryConverter.INSTANCE::poToVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
