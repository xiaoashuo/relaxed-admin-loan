package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.SysRoleMenu;
import com.relaxed.boot.common.system.model.vo.SysRoleMenuPageVO;
import com.relaxed.boot.common.system.model.qo.SysRoleMenuQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 角色菜单表
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysRoleMenuService extends ExtendService<SysRoleMenu> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysRoleMenuPageVO&gt; 分页数据
	 */
	PageResult<SysRoleMenuPageVO> queryPage(PageParam pageParam, SysRoleMenuQO qo);

	Boolean saveRoleMenus(String roleCode, Integer[] permissionIds);

	/**
	 * 更新某个菜单的 id
	 * @param originalId 原菜单ID
	 * @param menuId 修改后的菜单Id
	 * @return 被更新的菜单数
	 */
	int updateMenuId(Integer originalId, Integer menuId);

}
