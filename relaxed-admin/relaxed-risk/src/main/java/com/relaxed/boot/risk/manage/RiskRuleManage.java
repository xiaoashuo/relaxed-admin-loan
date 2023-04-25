package com.relaxed.boot.risk.manage;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.relaxed.boot.risk.core.distributor.EventDistributor;
import com.relaxed.boot.risk.core.event.RiskRuleChangeEvent;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeEnum;
import com.relaxed.boot.risk.enums.DataColumnType;
import com.relaxed.boot.risk.enums.RiskModelEnum;
import com.relaxed.boot.risk.model.converter.RiskRuleConverter;
import com.relaxed.boot.risk.model.dto.DataColumn;
import com.relaxed.boot.risk.model.entity.RiskRule;
import com.relaxed.boot.risk.model.entity.RiskRuleHistory;
import com.relaxed.boot.risk.model.qo.RiskRuleQO;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;
import com.relaxed.boot.risk.model.vo.RiskPreItemVO;
import com.relaxed.boot.risk.model.vo.RiskRuleVO;
import com.relaxed.boot.risk.rules.script.RuleScriptHandler;
import com.relaxed.boot.risk.service.RiskFieldService;
import com.relaxed.boot.risk.service.RiskPreItemService;
import com.relaxed.boot.risk.service.RiskRuleHistoryService;
import com.relaxed.boot.risk.service.RiskRuleService;
import com.relaxed.common.core.util.SpringUtils;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;

import com.relaxed.common.model.domain.SelectData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yakir
 * @Topic RuleManageService
 * @Description
 * @date 2021/9/24 14:34
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class RiskRuleManage {

	private final RiskRuleService ruleService;

	private final RiskRuleHistoryService ruleHistoryService;

	private final EventDistributor eventDistributor;

	private final RiskFieldService fieldService;

	private final RiskPreItemService preItemService;

	private final RuleScriptHandler ruleScriptHandler;

	public PageResult<RiskRuleVO> selectByPage(PageParam pageParam, RiskRuleQO ruleQO) {
		return ruleService.selectByPage(pageParam, ruleQO);
	}

	public boolean add(RiskRule rule) {
		String ruleName = rule.getName();
		RiskRule sqlRule = ruleService.getByName(ruleName);
		Assert.isNull(sqlRule, "rule has already exists.");
		rule.setStatus(RiskModelEnum.StatusEnum.INIT.getStatus());
		rule.setRuleScriptEntry("check");
		if (ruleService.save(rule)) {
			if (StringUtils.isEmpty(ruleName)) {
				rule.setName("rule_" + rule.getId());
				ruleService.updateById(rule);
			}
			SpringUtils.getContext().publishEvent(new RiskRuleChangeEvent(convertToRuleHistory(rule)));
			// 发布订阅
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_RULE_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskRuleConverter.INSTANCE.poToVo(rule)));
			return true;
		}
		return false;
	}

	private RiskRuleHistory convertToRuleHistory(RiskRule rule) {
		RiskRuleHistory ruleHistory = new RiskRuleHistory();
		ruleHistory.setRuleId(rule.getId());
		ruleHistory.setMerchantCode("system");
		ruleHistory.setLabel(rule.getLabel());
		ruleHistory.setInitScore(rule.getInitScore());
		ruleHistory.setBaseNum(rule.getBaseNum());
		ruleHistory.setOperator(rule.getOperator());
		ruleHistory.setRate(rule.getRate());
		ruleHistory.setRuleDefinition(rule.getRuleDefinition());
		ruleHistory.setCreatedTime(LocalDateTime.now());
		ruleHistory.setUpdatedTime(LocalDateTime.now());
		return ruleHistory;
	}

	public boolean edit(RiskRule rule) {
		RiskRule sqlRule = ruleService.getById(rule.getId());
		Assert.notNull(sqlRule, "rule  can not exists.");
		if (ruleService.updateById(rule)) {
			SpringUtils.getContext().publishEvent(new RiskRuleChangeEvent(convertToRuleHistory(rule)));
			String ruleJson = JSONUtil.toJsonStr(RiskRuleConverter.INSTANCE.poToVo(rule));
			if (!sqlRule.getScripts().equals(rule.getScripts())) {
				eventDistributor.distribute(SubscribeEnum.PUB_SUB_RULE_SCRIPT_CHANNEL.getChannel(), ruleJson);
			}
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_RULE_CHANNEL.getChannel(), ruleJson);
			return true;
		}
		return false;
	}

	public boolean del(Long id) {
		RiskRule rule = ruleService.getById(id);
		Assert.notNull(rule, "rule  can not exists.");
		if (ruleService.removeById(id)) {
			eventDistributor.distribute(SubscribeEnum.PUB_SUB_RULE_CHANNEL.getChannel(),
					JSONUtil.toJsonStr(RiskRuleConverter.INSTANCE.poToVo(rule)));
			return true;
		}
		return false;
	}

	public List<RiskRuleVO> listByActivationId(Long activationId) {
		return ruleService.listByActivationId(activationId);
	}

	public List<DataColumn> selectColumns(Long modelId) {
		List<DataColumn> dataColumns = new ArrayList<>();
		// 1.基础字段列
		DataColumn fieldDataColumn = new DataColumn(DataColumnType.FIELDS.getDesc(), DataColumnType.FIELDS.getName());
		List<RiskFieldVO> fieldVOS = fieldService.listByModelId(modelId);
		if (CollectionUtil.isNotEmpty(fieldVOS)) {
			List<DataColumn> fieldChildren = new ArrayList<>();
			fieldVOS.forEach(field -> fieldChildren
					.add(new DataColumn(field.getLabel(), field.getFieldName(), field.getFieldType())));
			fieldDataColumn.setChildren(fieldChildren);
		}
		dataColumns.add(fieldDataColumn);
		// 2.预处理字段列
		DataColumn parentPreItemDataColumn = new DataColumn(DataColumnType.PREITEMS.getDesc(),
				DataColumnType.PREITEMS.getName());
		List<RiskPreItemVO> preItemVOS = preItemService.listByModelId(modelId);
		if (CollectionUtil.isNotEmpty(preItemVOS)) {
			List<DataColumn> preItemChildren = new ArrayList<>();
			preItemVOS.forEach(preItem -> {
				DataColumn dataColumn = new DataColumn();
				dataColumn.setLabel(preItem.getDestLabel());
				dataColumn.setValue(preItem.getDestField());
				preItemChildren.add(dataColumn);
			});

		}
		dataColumns.add(parentPreItemDataColumn);
		// 3. 特征字段提取
		// DataColumn abstractionDataColumn = new
		// DataColumn(DataColumnType.ABSTRACTIONS.getDesc(),
		// DataColumnType.ABSTRACTIONS.getName());
		// List<AbstractionVO> abstractionVOS = abstractionService.listByModelId(modelId);
		// if (CollectionUtil.isNotEmpty(abstractionVOS)) {
		// List<DataColumn> preItemChildren = new ArrayList<>();
		// abstractionVOS.forEach(abstractionVO -> {
		// DataColumn dataColumn = new DataColumn();
		// dataColumn.setLabel(abstractionVO.getLabel());
		// dataColumn.setValue(abstractionVO.getName());
		// dataColumn.setType(FieldType.DOUBLE.name());
		// preItemChildren.add(dataColumn);
		// });
		// abstractionDataColumn.setChildren(preItemChildren);
		// }
		// dataColumns.add(abstractionDataColumn);
		return dataColumns;
	}

	public List<RiskRuleVO> listRule(Long activationId) {
		return ruleService.listByActivationId(activationId);
	}

	public List<SelectData<?>> getAllFieldList(Long modelId) {
		List<RiskFieldVO> riskFieldVOS = fieldService.listByModelId(modelId);
		List<RiskPreItemVO> riskPreItemVOS = preItemService.listByModelId(modelId);
		List<SelectData<?>> selectDataList = new ArrayList<>();
		for (RiskFieldVO riskFieldVO : riskFieldVOS) {
			String fieldName = riskFieldVO.getFieldName();
			String label = riskFieldVO.getLabel();
			SelectData selectData = new SelectData();
			selectData.setLabel(label);
			selectData.setValue(fieldName);
			// 字段
			selectData.setType("1");
			selectDataList.add(selectData);
		}
		for (RiskPreItemVO riskPreItemVO : riskPreItemVOS) {
			String destField = riskPreItemVO.getDestField();
			String destLabel = riskPreItemVO.getDestLabel();
			SelectData selectData = new SelectData();
			selectData.setLabel(destLabel);
			selectData.setValue(destField);
			// 预处理字段
			selectData.setType("2");
			selectDataList.add(selectData);
		}
		return selectDataList;
	}

	public boolean switchStatus(Long ruleId, Integer status) {
		RiskRule riskRule = ruleService.getById(ruleId);
		riskRule.setStatus(status);
		return ruleService.updateById(riskRule);
	}

}
