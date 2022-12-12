package com.relaxed.boot.web.system.manage;

import com.relaxed.boot.biz.system.service.SysRoleService;
import com.relaxed.boot.biz.system.service.SysUserRoleService;
import com.relaxed.boot.common.system.model.vo.SysRolePageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Yakir
 * @Topic SysRoleManage
 * @Description
 * @date 2022/11/19 14:51
 * @Version 1.0
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class SysRoleManage {

	private final SysUserRoleService sysUserRoleService;

	private final SysRoleService sysRoleService;

}
