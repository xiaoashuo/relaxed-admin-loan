package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.common.system.converter.SysRoleMenuConverter;
import com.relaxed.boot.common.system.model.entity.SysRoleMenu;
import com.relaxed.boot.common.system.model.qo.SysRoleMenuQO;
import com.relaxed.boot.common.system.model.vo.SysRoleMenuPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

/**
 * 角色菜单表
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysRoleMenuMapper extends ExtendMapper<SysRoleMenu> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysRoleMenuPageVO> VO分页数据
	 */
	default PageResult<SysRoleMenuPageVO> queryPage(PageParam pageParam, SysRoleMenuQO qo) {
		IPage<SysRoleMenu> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysRoleMenu> wrapper = WrappersX.lambdaQueryX(SysRoleMenu.class);
		this.selectPage(page, wrapper);
		IPage<SysRoleMenuPageVO> voPage = page.convert(SysRoleMenuConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	/**
	 * 根据角色标识删除角色权限关联关系
	 * @param roleCode 角色标识
	 */
	default void deleteByRoleCode(String roleCode) {
		this.delete(Wrappers.<SysRoleMenu>query().lambda().eq(SysRoleMenu::getRoleCode, roleCode));
	}

	/**
	 * 更新某个菜单的 id
	 * @param originalId 原菜单ID
	 * @param menuId 修改后的菜单Id
	 * @return 被更新的条数
	 */
	default int updateMenuId(Integer originalId, Integer menuId) {
		// @formatter:off
		LambdaUpdateWrapper<SysRoleMenu> wrapper = Wrappers.lambdaUpdate(SysRoleMenu.class)
				.set(SysRoleMenu::getMenuId, menuId)
				.eq(SysRoleMenu::getMenuId, originalId);
		// @formatter:on

		return this.update(null, wrapper);
	}

}
