package com.relaxed.boot.risk.manage;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.relaxed.boot.risk.core.distributor.EventDistributor;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeEnum;
import com.relaxed.boot.risk.core.handler.FieldValidateHandler;
import com.relaxed.boot.risk.core.plugins.PluginManager;
import com.relaxed.boot.risk.enums.FieldValidResult;
import com.relaxed.boot.risk.enums.RiskPreItemEnum;
import com.relaxed.boot.risk.exception.RiskCode;
import com.relaxed.boot.risk.exception.RiskException;
import com.relaxed.boot.risk.model.converter.RiskFieldConverter;
import com.relaxed.boot.risk.model.converter.RiskPreItemConverter;
import com.relaxed.boot.risk.model.entity.RiskField;
import com.relaxed.boot.risk.model.entity.RiskModel;
import com.relaxed.boot.risk.model.entity.RiskPreItem;
import com.relaxed.boot.risk.model.qo.RiskFieldQO;
import com.relaxed.boot.risk.model.qo.RiskPreItemQO;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;
import com.relaxed.boot.risk.model.vo.RiskPreItemVO;
import com.relaxed.boot.risk.service.RiskFieldService;
import com.relaxed.boot.risk.service.RiskModelService;
import com.relaxed.boot.risk.service.RiskPreItemService;

import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yakir
 * @Topic FieldManageServiceImpl
 * @Description
 * @date 2021/9/26 11:57
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class RiskFieldManage  {

	private final RiskFieldService fieldService;

	private final RiskPreItemService preItemService;

	private final RiskModelService modelService;

	private final EventDistributor eventDistributor;

	private final FieldValidateHandler fieldValidateHandler;


	public PageResult<RiskFieldVO> fieldList(PageParam pageParam, RiskFieldQO riskFieldQO) {
		return fieldService.selectByPage(pageParam,riskFieldQO);
	}

	public boolean fieldAdd(RiskField field) {
		Long modelId = field.getModelId();
		String fieldName = field.getFieldName();
		RiskField sqlField = fieldService.selectOne(modelId, fieldName);
		Assert.isNull(sqlField, "model field name already exists.");
		if (fieldService.save(field)) {
			// 发布订阅
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_FIELD_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskFieldConverter.INSTANCE.poToVo(field)));
			return true;
		}
		return false;
	}


	public boolean fieldEdit(RiskField field) {
		RiskField sqlField = fieldService.getById(field.getId());
		Assert.notNull(sqlField, "field can not exists.");
		if (fieldService.updateById(field)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_FIELD_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskFieldConverter.INSTANCE.poToVo(field)));
			return true;
		}
		return false;
	}


	public boolean fieldDel(Long id) {
		RiskField field = fieldService.getById(id);
		Assert.notNull(field, "field can not exists.");
		Long modelId = field.getModelId();
		RiskModel model = modelService.getById(modelId);
		Assert.notNull(field, "model can not exists.");
		String fieldName = field.getFieldName();
		if (model.getEntryName().equals(fieldName) || model.getReferenceDate().equals(fieldName)) {
			throw new RiskException(RiskCode.FIELD_NOT_ALLOWED_DEL);
		}
		if (fieldService.removeById(id)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_FIELD_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskFieldConverter.INSTANCE.poToVo(field)));
			return true;
		}
		return false;
	}


	public List<RiskPreItemVO> preItemListByModelId(Long modelId) {
		return preItemService.listByModelId(modelId);
	}


	public boolean preItemFieldAdd(RiskPreItem preItem) {
		RiskPreItem sqlPreItem = preItemService.getOne(preItem.getModelId(), preItem.getDestField());
		// 预处理字段已存在 则抛出异常
		Assert.isNull(sqlPreItem, "pre item has already exists.");
		// 保存预处理字段
		if (preItemService.save(preItem)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_PRE_ITEM_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskPreItemConverter.INSTANCE.poToVo(preItem)));
			return true;
		}
		return false;
	}


	public boolean preItemFieldEdit(RiskPreItem preItem) {
		RiskPreItem sqlPreItem = preItemService.getById(preItem.getId());
		Assert.notNull(sqlPreItem, "pre item can not be null.");
		if (preItemService.updateById(preItem)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_PRE_ITEM_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskPreItemConverter.INSTANCE.poToVo(preItem)));
			return true;
		}
		return false;
	}


	public boolean preItemFieldDel(Long modelId, Long id) {
		RiskPreItem preItem = preItemService.getOne(modelId, id);
		Assert.notNull(preItem, "pre item not exists.");
		if (preItemService.removeById(id)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_PRE_ITEM_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskPreItemConverter.INSTANCE.poToVo(preItem)));
			return true;
		}
		return false;
	}

	/**
	 * 验证字段合法性
	 * @param modelId
	 * @param data
	 * @return
	 */
	public Map<String, String> validate(Long modelId, Map data) {
		//TODO 此处可以做缓存
		List<RiskFieldVO> fieldVos = fieldService.listByModelId(modelId);
		Map<String, String> result = new HashMap<>(8);
		for (RiskFieldVO fieldVo : fieldVos) {
			FieldValidResult validResult = fieldValidateHandler.valid(fieldVo, data);
			if (validResult.isValid()) {
				continue;
			}
			result.put(validResult.getFieldName(), validResult.getErrorDesc());
		}
		return result;
	}

	public Map<String, Object> prepare(Long modelId, Map<String, Object> jsonInfo) {
		List<RiskPreItemVO> preItemVOList = preItemService.listByModelId(modelId);
		Map<String, Object> result = new HashMap<>(6);
		for (RiskPreItemVO preItemVO : preItemVOList) {
			if (!RiskPreItemEnum.StatusEnum.ENABLE.getStatus().equals(preItemVO.getStatus())) {
				continue;
			}
			String pluginName = preItemVO.getPlugin();
			if (StrUtil.isEmpty(pluginName)) {
				continue;
			}
			String sourceField = preItemVO.getSourceField();
			if (StrUtil.isEmpty(sourceField)) {
				continue;
			}
			String[] sourceFields = sourceField.split(StrPool.COMMA);
			// 转换后的结果
			Object transfer = PluginManager.of(pluginName).handle(preItemVO, jsonInfo, sourceFields);
			result.put(preItemVO.getDestField(), transfer);
		}
		return result;
	}


	public PageResult<RiskPreItemVO> preFieldList(PageParam pageParam,RiskPreItemQO riskPreItemQO) {
		return preItemService.selectByPage(pageParam,riskPreItemQO);
	}
}
