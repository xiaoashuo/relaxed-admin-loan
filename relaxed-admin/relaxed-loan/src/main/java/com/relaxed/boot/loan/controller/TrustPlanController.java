package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.TrustPlan;
import com.relaxed.boot.loan.model.qo.TrustPlanQO;
import com.relaxed.boot.loan.model.vo.TrustPlanPageVO;
import com.relaxed.boot.loan.service.TrustPlanService;
import com.relaxed.common.log.operation.annotation.CreateLog;
import com.relaxed.common.log.operation.annotation.DeleteLog;
import com.relaxed.common.log.operation.annotation.UpdateLog;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.common.model.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 信托计划配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/trust-plan")
@Tag(name = "信托计划配置表管理")
public class TrustPlanController {

	private final TrustPlanService trustPlanService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param trustPlanQO 信托计划配置表查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<TrustPlanPageVO>> getTrustPlanPage(PageParam pageParam, TrustPlanQO trustPlanQO) {
		return R.ok(trustPlanService.queryPage(pageParam, trustPlanQO));
	}

	/**
	 * 信托计划下拉列表
	 * @return R 通用返回体
	 */
	@Operation(summary = "信托计划下拉列表")
	@GetMapping("/select")
	public R<List<SelectData>> getTrustSelectedList() {
		return R.ok(trustPlanService.querySelectData());
	}

	/**
	 * 新增信托计划配置表
	 * @param trustPlan 信托计划配置表
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增信托计划配置表")
	@CreateLog(msg = "新增信托计划配置表")
	@PostMapping
	public R<Void> save(@RequestBody TrustPlan trustPlan) {
		return trustPlanService.save(trustPlan) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增信托计划配置表失败");
	}

	/**
	 * 修改信托计划配置表
	 * @param trustPlan 信托计划配置表
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改信托计划配置表")
	@UpdateLog(msg = "修改信托计划配置表")
	@PutMapping
	public R<Void> updateById(@RequestBody TrustPlan trustPlan) {
		return trustPlanService.updateById(trustPlan) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改信托计划配置表失败");
	}

	/**
	 * 通过id删除信托计划配置表
	 * @param trustPlanId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除信托计划配置表")
	@DeleteLog(msg = "通过id删除信托计划配置表")
	@DeleteMapping("/{trustPlanId}")
	public R<Void> removeById(@PathVariable("trustPlanId") Integer trustPlanId) {
		return trustPlanService.removeById(trustPlanId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除信托计划配置表失败");
	}

}
