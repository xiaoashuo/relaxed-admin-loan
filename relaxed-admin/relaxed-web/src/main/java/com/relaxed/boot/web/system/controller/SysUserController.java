package com.relaxed.boot.web.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.relaxed.boot.auth.component.SecurityHelper;
import com.relaxed.boot.auth.domain.SysUserDetails;
import com.relaxed.boot.biz.system.service.SysUserRoleService;
import com.relaxed.boot.common.system.model.dto.SysUserDTO;
import com.relaxed.boot.common.system.model.dto.SysUserScope;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.boot.common.system.model.qo.SysUserQO;
import com.relaxed.boot.common.system.model.qo.SysUserRoleQO;
import com.relaxed.boot.common.system.model.vo.SysUserPageVO;
import com.relaxed.boot.biz.system.service.SysUserService;
import com.relaxed.boot.common.system.model.vo.SysUserProfileVO;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageDetailVO;
import com.relaxed.boot.web.system.domain.ExcelSysUserVO;
import com.relaxed.boot.web.system.manage.SysUserManage;
import com.relaxed.common.core.validation.group.GroupType;
import com.relaxed.common.easyexcel.annotation.ResponseExcel;
import com.relaxed.common.log.operation.annotation.CreateLog;
import com.relaxed.common.log.operation.annotation.DeleteLog;
import com.relaxed.common.log.operation.annotation.Log;
import com.relaxed.common.log.operation.annotation.UpdateLog;
import com.relaxed.common.log.operation.enums.OperationTypes;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.common.model.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统用户
 *
 * @author yakir 2022-11-11 10:27:36
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/user")
@Tag(name = "系统用户管理")
public class SysUserController {

	private final SysUserService sysUserService;

	private final SysUserRoleService sysUserRoleService;

	private final SysUserManage sysUserManage;

	/**
	 * 分页查询
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/profile")
	public R<SysUserProfileVO> profile() {
		SysUserDetails sysUserDetails = SecurityHelper.getUser();
		return R.ok(sysUserManage.profile(sysUserDetails));
	}

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sysUserQO 系统用户查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@PostMapping("/page")
	public R<PageResult<SysUserPageVO>> getSysUserPage(PageParam pageParam, SysUserQO sysUserQO) {
		return R.ok(sysUserService.queryPage(pageParam, sysUserQO));
	}

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sysUserRoleQO 系统用户查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page/related/role")
	public R<PageResult<SysUserRolePageDetailVO>> getRelatedSysUserPageByRole(PageParam pageParam,
			SysUserRoleQO sysUserRoleQO) {
		return R.ok(sysUserService.queryRelatedSysUserPageByRole(pageParam, sysUserRoleQO));
	}

	/**
	 * 获取用户 所拥有的角色ID
	 * @param userId userId
	 */
	@GetMapping("/scope/{userId}")
	public R<SysUserScope> getUserRoleIds(@PathVariable("userId") Integer userId) {

		List<SysRole> roleList = sysUserRoleService.listRoles(userId);

		List<String> roleCodes = new ArrayList<>();
		if (CollectionUtil.isNotEmpty(roleList)) {
			roleList.forEach(role -> roleCodes.add(role.getCode()));
		}

		SysUserScope sysUserScope = new SysUserScope();
		sysUserScope.setRoleCodes(roleCodes);

		return R.ok(sysUserScope);
	}

	/**
	 * 修改用户权限信息 比如角色 数据权限等
	 * @param sysUserScope sysUserScope
	 * @return success/false
	 */
	@PutMapping("/scope/{userId}")
	@UpdateLog(msg = "系统用户授权")
	@Operation(summary = "系统用户授权", description = "系统用户授权")
	public R<Void> updateUserScope(@PathVariable("userId") Integer userId, @RequestBody SysUserScope sysUserScope) {
		return sysUserManage.updateUserScope(userId, sysUserScope) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "系统用户授权失败");
	}

	/**
	 * 新增系统用户
	 * @param sysUserDTO 系统用户
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增系统用户")
	@CreateLog(msg = "新增系统用户")
	@PostMapping
	public R<Void> save(@Validated({ Default.class, GroupType.CreateGroup.class }) @RequestBody SysUserDTO sysUserDTO) {
		SysUser user = sysUserService.getByUsername(sysUserDTO.getUsername());
		if (user != null) {
			return R.failed(BaseResultCode.LOGIC_CHECK_ERROR, "用户名已存在");
		}
		return sysUserManage.addSysUser(sysUserDTO) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增系统用户失败");

	}

	/**
	 * 修改系统用户
	 * @param sysUserDTO 系统用户
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改系统用户")
	@UpdateLog(msg = "修改系统用户")
	@PutMapping
	public R<Void> updateById(
			@Validated({ Default.class, GroupType.CreateGroup.class }) @RequestBody SysUserDTO sysUserDTO) {

		return sysUserManage.updateUser(sysUserDTO) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改系统用户失败");
	}

	/**
	 * 修改用户密码
	 * @author yakir
	 * @date 2022/11/27 15:09
	 * @param newPassword
	 * @return com.relaxed.common.model.result.R<java.lang.Boolean>
	 */
	@Operation(summary = "修改用户密码")
	@PatchMapping(value = "/change/password")
	public R<Boolean> updateUserPassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
		SysUserDetails user = SecurityHelper.getUser();
		Integer userId = user.getUserId();
		boolean result = sysUserManage.updateUserPassword(userId, oldPassword, newPassword);
		return R.ok(result);
	}

	/**
	 * 修改用户状态
	 * @author yakir
	 * @date 2022/11/27 15:10
	 * @param userId
	 * @param status
	 * @return com.relaxed.common.model.result.R<java.lang.Boolean>
	 */
	@Operation(summary = "修改用户状态")
	@PatchMapping(value = "/{userId}/status")
	public R<Boolean> updateUserPassword(@PathVariable Long userId, @RequestParam Integer status) {
		boolean result = sysUserService.updateUserStatus(userId, status);
		return R.ok(result);
	}

	/**
	 * 通过id删除系统用户
	 * @param userId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除系统用户")
	@DeleteLog(msg = "通过id删除系统用户")
	@DeleteMapping("/{userId}")
	public R<Void> removeById(@PathVariable("userId") Integer userId) {
		return sysUserService.removeById(userId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除系统用户失败");
	}

	/**
	 * 头像上传
	 */
	@Log(msg = "用户头像", type = OperationTypes.OTHER)
	@PostMapping("/avatar")
	public R avatar(@RequestParam("avatarfile") MultipartFile file) {

		SysUserDetails sysUserDetails = SecurityHelper.getUser();
		// 上传文件路径
		String url = sysUserManage.uploadAvatar(sysUserDetails, file);

		Map<String, String> data = new HashMap<>(2);
		data.put("url", url);
		return R.ok(data);
	}

	@ResponseExcel(name = "导出用户")
	@Operation(summary = "导出用户")
	@PostMapping("export")
	public List<ExcelSysUserVO> export(@RequestBody List<String> userIds, HttpServletResponse response) {
		response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		List<SysUser> sysUsers = sysUserService.listByIds(userIds);
		Assert.notEmpty(sysUsers, "导出列表不能为空");
		List<ExcelSysUserVO> sysUserVOS = sysUsers.stream()
				.map(sysUser -> BeanUtil.toBean(sysUser, ExcelSysUserVO.class)).collect(Collectors.toList());
		return sysUserVOS;
	}

}
