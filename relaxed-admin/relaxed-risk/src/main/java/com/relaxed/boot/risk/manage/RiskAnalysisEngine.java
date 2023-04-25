package com.relaxed.boot.risk.manage;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.relaxed.boot.common.system.utils.LogFormatUtil;
import com.relaxed.boot.risk.core.handler.RiskReportHandler;
import com.relaxed.boot.risk.core.risk.RiskEvalAsyncDistributor;
import com.relaxed.boot.risk.enums.RiskModelEnum;
import com.relaxed.boot.risk.exception.RiskCode;
import com.relaxed.boot.risk.exception.RiskEngineException;
import com.relaxed.boot.risk.model.vo.RiskModelVO;
import com.relaxed.boot.risk.rules.EvaluateContext;
import com.relaxed.boot.risk.rules.EvaluateReport;
import com.relaxed.boot.risk.rules.RiskEvaluateChain;
import com.relaxed.common.model.result.R;

import com.relaxed.common.redis.RedisHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Yakir
 * @Topic RiskAnalysisEngineService
 * @Description
 * @date 2021/8/29 8:46
 * @Version 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RiskAnalysisEngine {

	private final RiskModelManage riskModelManage;

	private final RiskFieldManage riskFieldManage;

	private final RiskEvaluateChain riskEvaluateChain;

	private final RiskReportHandler riskReportHandler;

	private final RiskEvalAsyncDistributor riskEvalAsyncDistributor;

	public R evaluateRisk(String modelGuid, String reqId, Map jsonInfo) {
		RiskModelVO modelVO = riskModelManage.getByGuid(modelGuid);
		// 1.基础规则效验
		if (modelVO == null) {
			return R.failed(RiskCode.MODEL_NOT_EXISTS);
		}
		if (!RiskModelEnum.StatusEnum.ENABLE.getStatus().equals(modelVO.getStatus())) {
			return R.failed(RiskCode.MODEL_DISABLED);
		}

		if (RiskModelEnum.FieldValidEnum.validField(modelVO.getFieldValidate())) {
			// 验证字段
			Map<String, String> validateMap = riskFieldManage.validate(modelVO.getId(), jsonInfo);
			if (CollectionUtil.isNotEmpty(validateMap)) {
				return R.failed(RiskCode.FIELD_VALID_NOT_PASSED).setData(validateMap);
			}
		}
		// 执行报告
		EvaluateReport evaluateReport = new EvaluateReport();
		try {
			// 2.预处理字段提取
			Map<String, Object> prepare = riskFieldManage.prepare(modelVO.getId(), jsonInfo);
			// 3.保存model event
			// modelEventManageService.save(modelVO, JSONUtil.toJsonStr(jsonInfo),
			// JSONUtil.toJsonStr(prepare),
			// EngineProperties.getDuplicate());
			// 4.执行分析
			EvaluateContext evaluateContext = new EvaluateContext();
			evaluateContext.setReqId(reqId);
			evaluateContext.setModelVo(modelVO);
			evaluateContext.setEventJson(jsonInfo);
			evaluateContext.setPreItemMap(prepare);
			boolean result;
			try {
				result = riskEvaluateChain.eval(evaluateContext, evaluateReport);
			}
			catch (Exception e) {
				return R.failed(RiskCode.RISK_EVAL_NOT_PASSED).setMessage(e.getMessage()).setData(evaluateReport);
			}
			if (!result) {
				return R.failed(RiskCode.RISK_EVAL_NOT_PASSED).setMessage("风控分析不通过").setData(evaluateReport);
			}
			// 5.for elastic analysis
			Long eventTimeMillis = Long.parseLong((String) jsonInfo.get(modelVO.getReferenceDate()));
			String timeStr = DateUtil.format(new Date(eventTimeMillis), "yyyy-MM-dd'T'HH:mm:ssZ");
			prepare.put("risk_ref_datetime", timeStr);
		}
		catch (Exception e) {
			log.error(LogFormatUtil.format("风控审核", "异常", LocalDateTime.now(), "模型id{}", modelGuid), e);
			throw new RiskEngineException("数据处理异常", e);
		}
		// 6. 缓存分析结果
		String jsonReport = JSONUtil.toJsonStr(evaluateReport);
		RedisHelper.set(buildReportCacheKey(modelGuid, reqId), jsonReport, 5 * 60);
		// 7. 保存事件信息和分析结果用于后续分析 可以发送到es redis 等等
		riskReportHandler.handle(modelGuid, reqId, jsonReport);
		return R.ok(evaluateReport);
	}

	public R evaluateRiskAsync(String modelGuid, String reqId, Map jsonInfo) {
		riskEvalAsyncDistributor.distribute(modelGuid, reqId, jsonInfo);
		// 直接返回受理成功 实际执行子任务去执行评估
		return R.ok();
	}

	public R evaluateReport(String modelGuid, String reqId) {
		String key = buildReportCacheKey(modelGuid, reqId);
		// 风控报告
		String jsonResult = RedisHelper.get(key);
		if (StrUtil.isEmpty(jsonResult)) {
			return R.failed(RiskCode.RISK_EVAL_EXPIRED);
		}
		return R.ok(JSONUtil.toBean(jsonResult, EvaluateReport.class));
	}

	private String buildReportCacheKey(String modelGuid, String reqId) {
		return "risk_engine_" + modelGuid + reqId;
	}

}
