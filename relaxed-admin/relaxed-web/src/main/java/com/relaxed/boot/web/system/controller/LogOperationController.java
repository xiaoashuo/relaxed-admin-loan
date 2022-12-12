package com.relaxed.boot.web.system.controller;

import com.relaxed.boot.common.system.model.entity.LogOperation;
import com.relaxed.boot.common.system.model.qo.LogOperationQO;
import com.relaxed.boot.common.system.model.vo.LogOperationPageVO;
import com.relaxed.boot.biz.system.service.LogOperationService;
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
 * 操作日志
 *
 * @author yakir 2022-11-11 10:27:36
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/log/operation")
@Tag(name = "操作日志管理")
public class LogOperationController {

	private final LogOperationService logOperationService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param logOperationQO 操作日志查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<LogOperationPageVO>> getLogOperationPage(PageParam pageParam, LogOperationQO logOperationQO) {
		return R.ok(logOperationService.queryPage(pageParam, logOperationQO));
	}

	/**
	 * 新增操作日志
	 * @param logOperation 操作日志
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增操作日志")
	@CreateLog(msg = "新增操作日志")
	@PostMapping
	public R<Void> save(@RequestBody LogOperation logOperation) {
		return logOperationService.save(logOperation) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增操作日志失败");
	}

	/**
	 * 修改操作日志
	 * @param logOperation 操作日志
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改操作日志")
	@UpdateLog(msg = "修改操作日志")
	@PutMapping
	public R<Void> updateById(@RequestBody LogOperation logOperation) {
		return logOperationService.updateById(logOperation) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改操作日志失败");
	}

	/**
	 * 通过id删除操作日志
	 * @param id id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除操作日志")
	@DeleteLog(msg = "通过id删除操作日志")
	@DeleteMapping("/{id}")
	public R<Void> removeById(@PathVariable("id") Long id) {
		return logOperationService.removeById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除操作日志失败");
	}

}
