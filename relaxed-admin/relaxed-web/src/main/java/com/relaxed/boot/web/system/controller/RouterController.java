package com.relaxed.boot.web.system.controller;

import cn.hutool.core.lang.Assert;
import com.relaxed.boot.auth.component.SecurityHelper;
import com.relaxed.boot.auth.domain.SysUserDetails;
import com.relaxed.boot.common.system.model.vo.RouterVO;
import com.relaxed.boot.web.system.manage.SysMenuManage;
import com.relaxed.common.model.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yakir
 * @Topic RouterController
 * @Description
 * @date 2022/11/11 14:00
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/router")
@Tag(name = "菜单路由控制器")
public class RouterController {

	private final SysMenuManage sysMenuManage;

	@Operation(summary = "路由列表")
	@GetMapping
	public R<List<RouterVO>> routerList() {
		SysUserDetails sysUserDetails = SecurityHelper.getUser();
		Assert.notNull(sysUserDetails, "获取登录用户信息失败");
		return R.ok(sysMenuManage.routerList(sysUserDetails));
	}

}
