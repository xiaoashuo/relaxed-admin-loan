package com.relaxed.boot.risk.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.mapper.RiskPluginMapper;
import com.relaxed.boot.risk.model.converter.RiskPluginConverter;
import com.relaxed.boot.risk.model.entity.RiskPlugin;
import com.relaxed.boot.risk.model.qo.RiskPluginQO;
import com.relaxed.boot.risk.model.vo.RiskPluginVO;
import com.relaxed.boot.risk.service.RiskPluginService;
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
 * @since 2021-09-28T13:43:11.834
 */
@RequiredArgsConstructor
@Service
public class RiskPluginServiceImpl extends ExtendServiceImpl<RiskPluginMapper, RiskPlugin> implements RiskPluginService {

	@Override
	public PageResult<RiskPluginVO> selectByPage(PageParam pageParam, RiskPluginQO pluginQO) {
		IPage<RiskPlugin> page = PageUtil.prodPage(pageParam);
		LambdaQueryWrapper<RiskPlugin> wrapper = Wrappers.lambdaQuery(RiskPlugin.class)
				.eq(ObjectUtil.isNotNull(pluginQO.getId()), RiskPlugin::getId, pluginQO.getId());
		this.baseMapper.selectPage(page, wrapper);
		IPage<RiskPluginVO> voPage = page.convert(RiskPluginConverter.INSTANCE::poToVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

}
