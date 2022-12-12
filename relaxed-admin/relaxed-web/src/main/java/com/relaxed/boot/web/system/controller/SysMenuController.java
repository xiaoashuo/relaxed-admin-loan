package com.relaxed.boot.web.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.relaxed.boot.common.system.constants.SysConstant;
import com.relaxed.boot.common.system.converter.SysMenuConverter;
import com.relaxed.boot.common.system.model.dto.SysMenuCreateDTO;
import com.relaxed.boot.common.system.model.dto.SysMenuUpdateDTO;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.qo.SysMenuGrantVO;
import com.relaxed.boot.common.system.model.qo.SysMenuQO;
import com.relaxed.boot.common.system.model.vo.SysMenuPageVO;
import com.relaxed.boot.biz.system.service.SysMenuService;
import com.relaxed.boot.web.system.manage.SysMenuManage;
import com.relaxed.common.core.util.tree.TreeUtils;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
 *
 * @author yakir 2022-11-11 10:27:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/menu")
@Tag(name = "菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101管理")
public class SysMenuController {

	private final SysMenuService sysMenuService;

	private final SysMenuManage sysMenuManage;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sysMenuQO 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<SysMenuPageVO>> getSysMenuPage(PageParam pageParam, SysMenuQO sysMenuQO) {
		return R.ok(sysMenuService.queryPage(pageParam, sysMenuQO));
	}

	/**
	 * 查询授权菜单列表
	 * @return R 通用返回体
	 */
	@GetMapping("/grant-list")
	@Operation(summary = "查询授权菜单列表", description = "查询授权菜单列表")
	public R<List<SysMenuGrantVO>> getSysMenuGrantList() {
		List<SysMenu> sysMenus = sysMenuService.list();
		if (CollectionUtil.isEmpty(sysMenus)) {
			R.ok(new ArrayList<>());
		}
		List<SysMenuGrantVO> voList = TreeUtils.buildTree(sysMenus, SysConstant.MENU_ROOT_ID,
				SysMenuConverter.INSTANCE::poToGrantVo);
		return R.ok(voList);
	}

	/**
	 * 新增菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
	 * @param sysMenuCreateDTO 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101")
	@CreateLog(msg = "新增菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101")
	@PostMapping
	public R<Void> save(@Valid @RequestBody SysMenuCreateDTO sysMenuCreateDTO) {
		return sysMenuManage.create(sysMenuCreateDTO) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增菜单权限失败");
	}

	/**
	 * 修改菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
	 * @param sysMenuUpdateDTO 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101")
	@UpdateLog(msg = "修改菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101")
	@PutMapping
	public R<Void> updateById(@RequestBody SysMenuUpdateDTO sysMenuUpdateDTO) {
		sysMenuManage.update(sysMenuUpdateDTO);
		return R.ok();
	}

	/**
	 * 通过id删除菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
	 * @param id id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101")
	@DeleteLog(msg = "通过id删除菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101")
	@DeleteMapping("/{id}")
	public R<Void> removeById(@PathVariable("id") Integer id) {
		return sysMenuService.removeById(id) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101失败");
	}

}
