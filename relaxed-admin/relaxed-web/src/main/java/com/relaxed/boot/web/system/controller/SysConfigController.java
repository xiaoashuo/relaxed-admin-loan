package com.relaxed.boot.web.system.controller;

import com.relaxed.boot.biz.system.service.SysConfigService;
import com.relaxed.boot.common.system.model.entity.SysConfig;
import com.relaxed.boot.common.system.model.qo.SysConfigQO;
import com.relaxed.boot.common.system.model.vo.SysConfigPageVO;
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
 * 基础配置
 *
 * @author yakir 2022-11-15 10:24:40
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/config")
@Tag(name = "基础配置管理")
public class SysConfigController {

	private final SysConfigService sysConfigService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sysConfigQO 基础配置查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<SysConfigPageVO>> getSysConfigPage(PageParam pageParam, SysConfigQO sysConfigQO) {
		return R.ok(sysConfigService.queryPage(pageParam, sysConfigQO));
	}

	/**
	 * 新增基础配置
	 * @param sysConfig 基础配置
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增基础配置")
	@CreateLog(msg = "新增基础配置")
	@PostMapping
	public R<Void> save(@RequestBody SysConfig sysConfig) {
		return sysConfigService.save(sysConfig) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增基础配置失败");
	}

	/**
	 * 修改基础配置
	 * @param sysConfig 基础配置
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改基础配置")
	@UpdateLog(msg = "修改基础配置")
	@PutMapping
	public R<Void> updateById(@RequestBody SysConfig sysConfig) {
		return sysConfigService.updateById(sysConfig) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改基础配置失败");
	}

	/**
	 * 通过id删除基础配置
	 * @param id id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除基础配置")
	@DeleteLog(msg = "通过id删除基础配置")
	@DeleteMapping("/{id}")
	public R<Void> removeById(@PathVariable("id") Integer id) {
		return sysConfigService.removeById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除基础配置失败");
	}

}
