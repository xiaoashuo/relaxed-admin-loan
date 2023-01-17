package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Project;
import com.relaxed.boot.loan.model.qo.ProjectQO;
import com.relaxed.boot.loan.model.vo.ProjectPageVO;
import com.relaxed.boot.loan.service.ProjectService;
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
 * 产品配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/project")
@Tag(name = "产品配置表管理")
public class ProjectController {

	private final ProjectService projectService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param projectQO 产品配置表查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<ProjectPageVO>> getProjectPage(PageParam pageParam, ProjectQO projectQO) {
		return R.ok(projectService.queryPage(pageParam, projectQO));
	}
	/**
	 * 信托项目下拉列表
	 * @return R 通用返回体
	 */
	@Operation(summary = "信托项目下拉列表")
	@GetMapping("/select")
	public R<List<SelectData>> getProductSelectedList() {
		return R.ok(projectService.querySelectData());
	}

	/**
	 * 新增产品配置表
	 * @param project 产品配置表
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增产品配置表")
	@CreateLog(msg = "新增产品配置表")
	@PostMapping
	public R<Void> save(@RequestBody Project project) {
		return projectService.save(project) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增产品配置表失败");
	}

	/**
	 * 修改产品配置表
	 * @param project 产品配置表
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改产品配置表")
	@UpdateLog(msg = "修改产品配置表")
	@PutMapping
	public R<Void> updateById(@RequestBody Project project) {
		return projectService.updateById(project) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改产品配置表失败");
	}

	/**
	 * 通过id删除产品配置表
	 * @param projectId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除产品配置表")
	@DeleteLog(msg = "通过id删除产品配置表")
	@DeleteMapping("/{projectId}")
	public R<Void> removeById(@PathVariable("projectId") Integer projectId) {
		return projectService.removeById(projectId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除产品配置表失败");
	}

}
