package com.relaxed.boot.web.system.controller;

import com.relaxed.boot.biz.system.service.LogLoginService;
import com.relaxed.boot.common.system.model.entity.LogLogin;
import com.relaxed.boot.common.system.model.qo.LogLoginQO;
import com.relaxed.boot.common.system.model.vo.LogLoginPageVO;
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
 * 登陆日志
 *
 * @author Yakir 2022-11-22 14:02:00
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/log/login")
@Tag(name = "登陆日志管理")
public class LogLoginController {

	private final LogLoginService logLoginService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param logLoginQO 登陆日志查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<LogLoginPageVO>> getLogLoginPage(PageParam pageParam, LogLoginQO logLoginQO) {
		return R.ok(logLoginService.queryPage(pageParam, logLoginQO));
	}

	/**
	 * 新增登陆日志
	 * @param logLogin 登陆日志
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增登陆日志")
	@CreateLog(msg = "新增登陆日志")
	@PostMapping
	public R<Void> save(@RequestBody LogLogin logLogin) {
		return logLoginService.save(logLogin) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增登陆日志失败");
	}

	/**
	 * 修改登陆日志
	 * @param logLogin 登陆日志
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改登陆日志")
	@UpdateLog(msg = "修改登陆日志")
	@PutMapping
	public R<Void> updateById(@RequestBody LogLogin logLogin) {
		return logLoginService.updateById(logLogin) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改登陆日志失败");
	}

	/**
	 * 通过id删除登陆日志
	 * @param id id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除登陆日志")
	@DeleteLog(msg = "通过id删除登陆日志")
	@DeleteMapping("/{id}")
	public R<Void> removeById(@PathVariable("id") Long id) {
		return logLoginService.removeById(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除登陆日志失败");
	}

}
