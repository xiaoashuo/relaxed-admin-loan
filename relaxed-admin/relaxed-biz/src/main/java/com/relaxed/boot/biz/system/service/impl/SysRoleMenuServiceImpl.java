package com.relaxed.boot.biz.system.service.impl;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.relaxed.boot.common.system.model.entity.SysRoleMenu;
import com.relaxed.boot.common.system.model.vo.SysRoleMenuPageVO;
import com.relaxed.boot.common.system.model.qo.SysRoleMenuQO;
import com.relaxed.boot.biz.system.mapper.SysRoleMenuMapper;
import com.relaxed.boot.biz.system.service.SysRoleMenuService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色菜单表
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Service
public class SysRoleMenuServiceImpl extends ExtendServiceImpl<SysRoleMenuMapper, SysRoleMenu>
		implements SysRoleMenuService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysRoleMenuPageVO> 分页数据
	 */
	@Override
	public PageResult<SysRoleMenuPageVO> queryPage(PageParam pageParam, SysRoleMenuQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Transactional(rollbackFor = { Exception.class })
	@Override
	public Boolean saveRoleMenus(String roleCode, Integer[] menuIds) {
		baseMapper.deleteByRoleCode(roleCode);
		if (menuIds != null && menuIds.length != 0) {
			List<SysRoleMenu> list = Arrays.stream(menuIds).map((menuId) -> new SysRoleMenu(roleCode, menuId))
					.collect(Collectors.toList());
			int i = baseMapper.insertBatchSomeColumn(list);
			return SqlHelper.retBool(i);
		}
		else {
			return Boolean.TRUE;
		}
	}

	/**
	 * 更新某个菜单的 id
	 * @param originalId 原菜单ID
	 * @param menuId 修改后的菜单Id
	 * @return 被更新的菜单数
	 */
	@Override
	public int updateMenuId(Integer originalId, Integer menuId) {
		return baseMapper.updateMenuId(originalId, menuId);
	}

}
