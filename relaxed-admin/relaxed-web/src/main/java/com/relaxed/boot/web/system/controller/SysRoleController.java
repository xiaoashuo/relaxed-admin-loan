package com.relaxed.boot.web.system.controller;

import com.relaxed.boot.biz.system.service.SysMenuService;
import com.relaxed.boot.biz.system.service.SysRoleMenuService;
import com.relaxed.boot.biz.system.service.SysUserRoleService;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.qo.SysRoleQO;
import com.relaxed.boot.common.system.model.vo.SysRolePageVO;
import com.relaxed.boot.biz.system.service.SysRoleService;
import com.relaxed.boot.web.system.manage.SysRoleManage;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色
 *
 * @author yakir 2022-11-11 10:27:53
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/role")
@Tag(name = "系统角色管理")
public class SysRoleController {

	private final SysRoleService sysRoleService;

	private final SysMenuService sysMenuService;

	private final SysUserRoleService sysUserRoleService;

	private final SysRoleMenuService sysRoleMenuService;

	private final SysRoleManage sysRoleManage;

	/**
	 * 根据身份id 获取身份信息
	 * @author yakir
	 * @date 2022/11/19 16:46
	 * @param roleId
	 * @return com.relaxed.common.model.result.R<com.relaxed.boot.common.system.model.vo.SysRolePageVO>
	 */
	@Operation(summary = "根据id获取身份信息")
	@GetMapping
	public R<SysRolePageVO> getSysRoleByRoleId(Integer roleId) {
		return R.ok(sysRoleService.getSysRoleByRoleId(roleId));
	}

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sysRoleQO 系统角色查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<SysRolePageVO>> getSysRolePage(PageParam pageParam, SysRoleQO sysRoleQO) {
		return R.ok(sysRoleService.queryPage(pageParam, sysRoleQO));
	}

	/**
	 * 新增系统角色
	 * @param sysRole 系统角色
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增系统角色")
	@CreateLog(msg = "新增系统角色")
	@PostMapping
	public R<Void> save(@RequestBody SysRole sysRole) {
		return sysRoleService.save(sysRole) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增系统角色失败");
	}

	/**
	 * 修改系统角色
	 * @param sysRole 系统角色
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改系统角色")
	@UpdateLog(msg = "修改系统角色")
	@PutMapping
	public R<Void> updateById(@RequestBody SysRole sysRole) {
		return sysRoleService.updateById(sysRole) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改系统角色失败");
	}

	/**
	 * 通过id删除系统角色
	 * @param id id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除系统角色")
	@DeleteLog(msg = "通过id删除系统角色")
	@DeleteMapping("/{id}")
	public R<Void> removeById(@PathVariable("id") Integer id) {
		return sysRoleService.removeById(id) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除系统角色失败");
	}

	/**
	 * 更新角色权限
	 * @param roleCode 角色Code
	 * @param permissionIds 权限ID数组
	 * @return success、false
	 */
	@PutMapping("/permission/code/{roleCode}")
	@UpdateLog(msg = "更新角色权限")
	@Operation(summary = "更新角色权限", description = "更新角色权限")
	public R<Boolean> savePermissionIds(@PathVariable("roleCode") String roleCode,
			@RequestBody Integer[] permissionIds) {
		return R.ok(sysRoleMenuService.saveRoleMenus(roleCode, permissionIds));
	}

	/**
	 * 返回角色的菜单集合
	 * @param roleCode 角色ID
	 * @return 属性集合
	 */
	@GetMapping("/permission/code/{roleCode}")
	public R<List<Integer>> getPermissionIds(@PathVariable("roleCode") String roleCode) {
		List<SysMenu> sysMenus = sysMenuService.listByRoleCode(roleCode);
		List<Integer> menuIds = sysMenus.stream().map(SysMenu::getId).collect(Collectors.toList());
		return R.ok(menuIds);
	}

	/**
	 * 获取角色列表
	 * @return 角色列表
	 */
	@GetMapping("/select")
	public R<List<SelectData<Void>>> listSelectData() {
		return R.ok(sysRoleService.listSelectData());
	}

	/**
	 * 解绑与用户绑定关系
	 * @return R
	 */
	@DeleteMapping("/user")
	@Operation(summary = "解绑与用户绑定关系", description = "解绑与用户绑定关系")
	public R<Boolean> unbindRoleUser(@RequestParam("userId") Integer userId,
			@RequestParam("roleCode") String roleCode) {
		return R.ok(sysUserRoleService.unbindRoleUser(userId, roleCode));
	}

}
