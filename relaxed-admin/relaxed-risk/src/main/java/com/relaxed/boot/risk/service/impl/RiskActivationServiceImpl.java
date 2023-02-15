package com.relaxed.boot.risk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.mapper.RiskActivationMapper;
import com.relaxed.boot.risk.model.converter.RiskActivationConverter;
import com.relaxed.boot.risk.model.entity.RiskActivation;
import com.relaxed.boot.risk.model.qo.RiskActivationQO;
import com.relaxed.boot.risk.model.vo.RiskActivationVO;
import com.relaxed.boot.risk.service.RiskActivationService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.extend.mybatis.plus.toolkit.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务层实现
 * </p>
 *
 * @author Yakir
 * @since 2021-08-29T18:48:19.435
 */
@RequiredArgsConstructor
@Service
public class RiskActivationServiceImpl extends ExtendServiceImpl<RiskActivationMapper, RiskActivation>
		implements RiskActivationService {

	@Override
	public PageResult<RiskActivationVO> selectByPage(PageParam pageParam, RiskActivationQO activationQO) {
		IPage<RiskActivation> page = PageUtil.prodPage(pageParam);
		LambdaQueryWrapper<RiskActivation> wrapper = Wrappers.lambdaQuery(RiskActivation.class)
				.eq(ObjectUtil.isNotNull(activationQO.getId()), RiskActivation::getId, activationQO.getId());
		this.baseMapper.selectPage(page, wrapper);
		IPage<RiskActivationVO> voPage = page.convert(RiskActivationConverter.INSTANCE::poToVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	@Override
	public List<RiskActivationVO> listByModelId(Long modelId) {
		List<RiskActivation> activations = baseMapper.listByModelId(modelId);
		return RiskActivationConverter.INSTANCE.poToVOs(activations);
	}

}
