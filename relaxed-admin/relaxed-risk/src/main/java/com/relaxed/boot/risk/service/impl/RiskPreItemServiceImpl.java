package com.relaxed.boot.risk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.mapper.RiskPreItemMapper;
import com.relaxed.boot.risk.model.converter.RiskPreItemConverter;
import com.relaxed.boot.risk.model.entity.RiskPreItem;
import com.relaxed.boot.risk.model.qo.RiskPreItemQO;
import com.relaxed.boot.risk.model.vo.RiskPreItemVO;
import com.relaxed.boot.risk.service.RiskPreItemService;
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
 * @since 2021-08-29T13:57:50.664
 */
@RequiredArgsConstructor
@Service
public class RiskPreItemServiceImpl extends ExtendServiceImpl<RiskPreItemMapper, RiskPreItem>
		implements RiskPreItemService {

	@Override
	public PageResult<RiskPreItemVO> selectByPage(PageParam pageParam, RiskPreItemQO preItemQO) {
		IPage<RiskPreItem> page = PageUtil.prodPage(pageParam);
		LambdaQueryWrapperX<RiskPreItem> wrapper = WrappersX.lambdaQueryX(RiskPreItem.class);
		wrapper.eqIfPresent(RiskPreItem::getSourceField, preItemQO.getSourceField());
		wrapper.eqIfPresent(RiskPreItem::getDestField, preItemQO.getDestField());
		wrapper.orderByDesc(RiskPreItem::getId);
		this.baseMapper.selectPage(page, wrapper);
		IPage<RiskPreItemVO> voPage = page.convert(RiskPreItemConverter.INSTANCE::poToVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	@Override
	public List<RiskPreItemVO> listByModelId(Long modelId) {
		List<RiskPreItem> preItems = baseMapper.getByModelId(modelId);
		return preItems != null ? RiskPreItemConverter.INSTANCE.poToVOs(preItems) : null;
	}

	@Override
	public RiskPreItem getOne(Long modelId, String destField) {
		return baseMapper.selectOne(modelId, destField);
	}

	@Override
	public RiskPreItem getOne(Long modelId, Long preItemId) {
		return baseMapper.selectOne(modelId, preItemId);
	}

}
