package com.relaxed.boot.risk.rules.extractor;

import cn.hutool.json.JSONUtil;
import com.relaxed.boot.risk.enums.FieldType;
import com.relaxed.boot.risk.rules.EvaluateContext;
import com.relaxed.boot.risk.rules.EvaluateReport;


import java.util.Map;

/**
 * @author Yakir
 * @Topic DefaultFieldExtractor
 * @Description
 * @date 2021/8/30 13:35
 * @Version 1.0
 */

public class SimpleFieldExtractor implements FieldExtractor {

	@Override
	public String extractorFieldName(String originText) {
		return originText.replace("fields.", "").replace("preItems.", "");
	}

	private <T> T extractorFieldValue(String fieldName, Map... maps) {
		for (Map map : maps) {
			if (map == null) {
				continue;
			}
			T searchFieldVal = JSONUtil.getByPath(JSONUtil.parseObj(map), fieldName, null);
			if (searchFieldVal != null) {
				return searchFieldVal;
			}
		}
		return null;
	}

	@Override
	public <T> T extractorFieldValue(String fieldName, EvaluateContext evaluateContext, EvaluateReport evaluateReport) {
		Map eventJson = evaluateContext.getEventJson();

		Map<String, Object> preItemMap = evaluateContext.getPreItemMap();
//		// 抽象特征
//		Map<String, ?> abstractionMAP = evaluateReport.getEvaluateData().get(AbstractionRiskEvaluate.ABSTRACTIONS);

		return extractorFieldValue(fieldName, eventJson, preItemMap);
	}

	@Override
	public FieldType extractorFieldType(String fieldName, Map<String, String> fieldType) {
		String name = fieldType.get(fieldName);
		return name == null ? null : FieldType.valueOf(name);
	}

}
