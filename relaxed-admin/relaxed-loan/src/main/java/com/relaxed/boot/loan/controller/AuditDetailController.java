package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.AuditDetail;
import com.relaxed.boot.loan.model.qo.AuditDetailQO;
import com.relaxed.boot.loan.model.vo.AuditDetailPageVO;
import com.relaxed.boot.loan.service.AuditDetailService;
import com.relaxed.common.log.operation.annotation.CreateLog;
import com.relaxed.common.log.operation.annotation.DeleteLog;
import com.relaxed.common.log.operation.annotation.UpdateLog;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
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

/**
 * 审核拒绝原因表
 *
 * @author yakir 2023-01-10 10:32:31
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/audit-detail")
@Tag(name = "审核拒绝原因表管理")
public class AuditDetailController {

	private final AuditDetailService auditDetailService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param auditDetailQO 审核拒绝原因表查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<AuditDetailPageVO>> getAuditDetailPage(PageParam pageParam, AuditDetailQO auditDetailQO) {
		return R.ok(auditDetailService.queryPage(pageParam, auditDetailQO));
	}

	/**
	 * 新增审核拒绝原因表
	 * @param auditDetail 审核拒绝原因表
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增审核拒绝原因表")
	@CreateLog(msg = "新增审核拒绝原因表")
	@PostMapping
	public R<Void> save(@RequestBody AuditDetail auditDetail) {
		return auditDetailService.save(auditDetail) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增审核拒绝原因表失败");
	}

	/**
	 * 修改审核拒绝原因表
	 * @param auditDetail 审核拒绝原因表
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改审核拒绝原因表")
	@UpdateLog(msg = "修改审核拒绝原因表")
	@PutMapping
	public R<Void> updateById(@RequestBody AuditDetail auditDetail) {
		return auditDetailService.updateById(auditDetail) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改审核拒绝原因表失败");
	}

	/**
	 * 通过id删除审核拒绝原因表
	 * @param auditDetailId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除审核拒绝原因表")
	@DeleteLog(msg = "通过id删除审核拒绝原因表")
	@DeleteMapping("/{auditDetailId}")
	public R<Void> removeById(@PathVariable("auditDetailId") Long auditDetailId) {
		return auditDetailService.removeById(auditDetailId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除审核拒绝原因表失败");
	}

}
