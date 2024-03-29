package com.relaxed.boot.risk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.mapper.RiskFieldMapper;
import com.relaxed.boot.risk.model.converter.RiskFieldConverter;
import com.relaxed.boot.risk.model.entity.RiskField;
import com.relaxed.boot.risk.model.qo.RiskFieldQO;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;
import com.relaxed.boot.risk.service.RiskFieldService;
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
 * @since 2021-08-29T12:14:38.328
 */
@RequiredArgsConstructor
@Service
public class RiskFieldServiceImpl extends ExtendServiceImpl<RiskFieldMapper, RiskField> implements RiskFieldService {

	@Override
	public PageResult<RiskFieldVO> selectByPage(PageParam pageParam, RiskFieldQO fieldQO) {
		IPage<RiskField> page = PageUtil.prodPage(pageParam);
		LambdaQueryWrapperX<RiskField> wrapper = WrappersX.lambdaQueryX(RiskField.class);
		wrapper.eq(ObjectUtil.isNotNull(fieldQO.getModelId()), RiskField::getModelId, fieldQO.getModelId())
				.eqIfPresent(RiskField::getFieldName, fieldQO.getFieldName())
				.eqIfPresent(RiskField::getLabel, fieldQO.getLabel());
		wrapper.orderByDesc(RiskField::getId);
		this.baseMapper.selectPage(page, wrapper);
		IPage<RiskFieldVO> voPage = page.convert(RiskFieldConverter.INSTANCE::poToVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	@Override
	public List<RiskFieldVO> listByModelId(Long modelId) {
		List<RiskField> list = baseMapper.listByModelId(modelId);
		return list != null ? RiskFieldConverter.INSTANCE.poToVOs(list) : null;
	}

	@Override
	public RiskField selectOne(Long modelId, String fieldName) {
		return baseMapper.selectOne(modelId, fieldName);
	}

}
