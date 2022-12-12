package com.relaxed.boot.web.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.relaxed.boot.biz.system.service.SysOrganizationService;
import com.relaxed.boot.common.system.converter.SysOrganizationConverter;
import com.relaxed.boot.common.system.model.dto.SysOrganizationDTO;
import com.relaxed.boot.common.system.model.entity.SysOrganization;
import com.relaxed.boot.common.system.model.qo.SysOrganizationQO;
import com.relaxed.boot.common.system.model.vo.SysOrganizationPageVO;

import com.relaxed.boot.common.system.model.vo.SysOrganizationTree;
import com.relaxed.boot.common.system.model.vo.SysOrganizationVO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织架构
 *
 * @author Yakir 2022-11-23 17:56:28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/organization")
@Tag(name = "组织架构管理")
public class SysOrganizationController {

	private final SysOrganizationService sysOrganizationService;

	/**
	 * 组织架构列表查询
	 * @return R 通用返回体
	 */
	@GetMapping("/list")
	@Operation(summary = "组织架构列表查询")
	public R<List<SysOrganizationVO>> listOrganization() {
		List<SysOrganization> list = sysOrganizationService.list();
		if (CollectionUtil.isEmpty(list)) {
			return R.ok(new ArrayList<>());
		}
		List<SysOrganizationVO> voList = list.stream().map(SysOrganizationConverter.INSTANCE::poToVo)
				.collect(Collectors.toList());
		return R.ok(voList);
	}

	/**
	 * 组织架构树查询
	 * @param qo 组织机构查询条件
	 * @return R 通用返回体
	 */
	@GetMapping("/tree")
	@Operation(summary = "组织架构树查询")
	public R<List<SysOrganizationTree>> getOrganizationTree(SysOrganizationQO qo) {
		return R.ok(sysOrganizationService.listTree(qo));
	}

	/**
	 * 新增组织架构
	 * @param sysOrganizationDTO 组织机构DTO
	 * @return R 通用返回体
	 */
	@CreateLog(msg = "新增组织架构")
	@PostMapping
	@Operation(summary = "新增组织架构")
	public R<Void> save(@RequestBody SysOrganizationDTO sysOrganizationDTO) {
		return sysOrganizationService.create(sysOrganizationDTO) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增组织架构失败");
	}

	/**
	 * 修改组织架构
	 * @param sysOrganization 组织架构
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改组织架构")
	@UpdateLog(msg = "修改组织架构")
	@PutMapping
	public R<Void> updateById(@RequestBody SysOrganization sysOrganization) {
		return sysOrganizationService.updateById(sysOrganization) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改组织架构失败");
	}

	/**
	 * 通过id删除组织架构
	 * @param id id
	 * @return R 通用返回体
	 */
	@DeleteLog(msg = "通过id删除组织架构")
	@DeleteMapping("/{id}")
	@Operation(summary = "通过id删除组织架构")
	public R<Void> removeById(@PathVariable("id") Integer id) {
		return sysOrganizationService.removeById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除组织架构失败");
	}

}
