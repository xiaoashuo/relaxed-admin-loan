package com.relaxed.boot.risk.manage;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.relaxed.boot.risk.core.distributor.EventDistributor;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeEnum;
import com.relaxed.boot.risk.enums.RiskModelEnum;
import com.relaxed.boot.risk.model.converter.RiskModelConverter;
import com.relaxed.boot.risk.model.entity.RiskModel;
import com.relaxed.boot.risk.model.qo.RiskModelQO;
import com.relaxed.boot.risk.model.vo.RiskModelVO;
import com.relaxed.boot.risk.service.RiskModelService;

import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Yakir
 * @Topic ModelManageServiceImpl
 * @Description
 * @date 2021/9/28 11:45
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class RiskModelManage {

	private final RiskModelService modelService;

	private final EventDistributor eventDistributor;

	public PageResult<RiskModelVO> selectByPage(PageParam pageParam, RiskModelQO modelQO) {
		return modelService.selectByPage(pageParam, modelQO);
	}

	public boolean add(RiskModel RiskModel) {
		String modelName = RiskModel.getModelName();
		RiskModel sqlModel = modelService.getByModelName(modelName);
		Assert.isNull(sqlModel, "RiskModel name has already exists.");
		RiskModel.setGuid(IdUtil.simpleUUID().toUpperCase());
		RiskModel.setStatus(RiskModelEnum.StatusEnum.INIT.getStatus());
		if (modelService.save(RiskModel)) {
			// 发布订阅
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_MODEL_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskModelConverter.INSTANCE.poToVo(RiskModel)));
			return true;
		}
		return false;
	}

	public boolean edit(RiskModel RiskModel) {
		return false;
	}

	public boolean del(Long id) {
		RiskModel RiskModel = modelService.getById(id);
		Assert.notNull(RiskModel, "RiskModel not exists.");
		// 模型为模板 则不允许删除
		// Assert.state(!ModelEnums.TemplateEnum.isTemplate(RiskModel.getTemplate()),
		// "RiskModel is template,not allowed del.");
		if (modelService.removeById(id)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_MODEL_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskModelConverter.INSTANCE.poToVo(RiskModel)));
			return true;
		}
		return false;
	}

	public RiskModelVO getByGuid(String modelGuid) {
		return modelService.getByGuid(modelGuid);
	}

	public RiskModelVO getByModelId(Long modelId) {
		RiskModel riskModel = modelService.getById(modelId);
		return RiskModelConverter.INSTANCE.poToVo(riskModel);
	}

	public boolean switchStatus(Long modelId, Integer status) {
		RiskModel riskModel = modelService.getById(modelId);
		Assert.notNull(riskModel, "模型信息不存在");
		riskModel.setStatus(status);
		return modelService.updateById(riskModel);
	}

}
