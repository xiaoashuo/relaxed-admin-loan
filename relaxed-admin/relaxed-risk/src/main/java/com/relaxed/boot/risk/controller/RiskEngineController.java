package com.relaxed.boot.risk.controller;

import com.relaxed.boot.risk.manage.RiskAnalysisEngine;
import com.relaxed.boot.risk.model.dto.EventRequest;
import com.relaxed.common.model.result.R;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yakir
 * @Topic RiskController
 * @Description 接受用户事件数据，实时进行分析并返回分析结果。
 * @date 2021/9/7 10:43
 * @Version 1.0
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/services/risk/v1")
@Tag(name = "RiskApi", description = "风险分析API(引擎端)")
public class RiskEngineController {

	private final RiskAnalysisEngine riskAnalysisEngineService;

	@Operation(summary = "查询事件处理结果")
	@GetMapping("/getScore")
	public R getScore(
			@RequestParam @Parameter(name = "modelGuid", description = "模型Guid", required = true) String modelGuid,
			@RequestParam @Parameter(name = "reqId", description = "请求流水号", required = true) String reqId) {
		return riskAnalysisEngineService.evaluateReport(modelGuid, reqId);
	}

	@Operation(summary = "事件数据提交接口")
	@PostMapping("/upload")
	public R upload(@Validated @RequestBody EventRequest request) {
		return riskAnalysisEngineService.evaluateRisk(request.getGuid(), request.getReqId(), request.getJsonInfo());
	}

	@Operation(summary = "事件数据提交接口")
	@PostMapping("/upload/async")
	public R asyncUpload(@Validated @RequestBody EventRequest request) {
		return riskAnalysisEngineService.evaluateRiskAsync(request.getGuid(), request.getReqId(),
				request.getJsonInfo());
	}

}
