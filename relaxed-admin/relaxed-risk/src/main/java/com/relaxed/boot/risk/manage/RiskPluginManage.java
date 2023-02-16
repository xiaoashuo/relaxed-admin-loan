package com.relaxed.boot.risk.manage;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.risk.enums.RiskPluginEnum;
import com.relaxed.boot.risk.model.converter.RiskPluginConverter;
import com.relaxed.boot.risk.model.entity.RiskPlugin;
import com.relaxed.boot.risk.model.qo.RiskPluginQO;
import com.relaxed.boot.risk.model.vo.RiskPluginVO;
import com.relaxed.boot.risk.service.RiskPluginService;
import com.relaxed.common.model.domain.PageParam;

import com.relaxed.common.model.domain.PageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yakir
 * @Topic PluginManageServiceImpl
 * @Description
 * @date 2021/9/28 13:54
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class RiskPluginManage  {

	private final RiskPluginService pluginService;


	public List<RiskPluginVO> listEnabledPlugins() {
		List<RiskPlugin> list = pluginService.list(
				Wrappers.lambdaQuery(RiskPlugin.class).eq(RiskPlugin::getStatus, RiskPluginEnum.StatusEnum.ENABLE.getStatus()));
		return RiskPluginConverter.INSTANCE.poToVOs(list);
	}


	public PageResult<RiskPluginVO> selectByPage(PageParam pageParam, RiskPluginQO pluginQO) {
		return pluginService.selectByPage(pageParam, pluginQO);
	}


	public boolean add(RiskPlugin plugin) {
		return pluginService.save(plugin);
	}


	public boolean edit(RiskPlugin plugin) {
		return pluginService.updateById(plugin);
	}


	public boolean del(Long id) {
		return pluginService.removeById(id);
	}

}
