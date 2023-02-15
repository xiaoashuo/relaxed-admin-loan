package com.relaxed.boot.risk.manage;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.relaxed.boot.risk.core.distributor.EventDistributor;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeEnum;
import com.relaxed.boot.risk.exception.RiskCode;
import com.relaxed.boot.risk.exception.RiskException;
import com.relaxed.boot.risk.model.converter.RiskActivationConverter;
import com.relaxed.boot.risk.model.dto.RiskActivationDTO;
import com.relaxed.boot.risk.model.entity.RiskActivation;
import com.relaxed.boot.risk.model.qo.RiskActivationQO;
import com.relaxed.boot.risk.model.vo.RiskActivationVO;
import com.relaxed.boot.risk.service.RiskActivationService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.function.Supplier;

/**
 * @author Yakir
 * @Topic ActivationManageService
 * @Description
 * @date 2021/9/27 18:04
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class RiskActivationManage  {

	private final RiskActivationService activationService;

	private final EventDistributor eventDistributor;


	public PageResult<RiskActivationVO> selectByPage(PageParam pageParam, RiskActivationQO activationQO) {
		return activationService.selectByPage(pageParam, activationQO);
	}


	public RiskActivationDTO add(RiskActivation activation) {
		Assert.isTrue(activationService.save(activation), () -> new RiskException(RiskCode.RISK_ACTIVATION_INFO_EXCEPTION));
		activation.setActivationName("activation_"+activation.getId());
		activationService.updateById(activation);
		eventDistributor.distribute(SubscribeEnum.PUB_SUB_ACTIVATION_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskActivationConverter.INSTANCE.poToVo(activation)));
		return RiskActivationConverter.INSTANCE.poToDto(activation);
	}


	public boolean edit(RiskActivation activation) {
		Long activationId = activation.getId();
		RiskActivation sqlActivation = activationService.getById(activationId);
		Assert.notNull(sqlActivation, "activation can not exists.");
		if (activationService.updateById(activation)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_ACTIVATION_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskActivationConverter.INSTANCE.poToVo(activation)));
			return true;
		}
		return false;
	}


	public boolean del(Long id) {
		RiskActivation sqlActivation = activationService.getById(id);
		Assert.notNull(sqlActivation, "activation can not exists.");
		if (activationService.removeById(id)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_ACTIVATION_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskActivationConverter.INSTANCE.poToVo(sqlActivation)));
			return true;
		}
		return false;
	}

}
