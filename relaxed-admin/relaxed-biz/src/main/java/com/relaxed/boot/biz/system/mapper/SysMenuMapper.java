package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.relaxed.boot.common.system.converter.SysMenuConverter;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.qo.SysMenuQO;
import com.relaxed.boot.common.system.model.vo.SysMenuPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

import java.util.List;

/**
 * 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysMenuMapper extends ExtendMapper<SysMenu> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysMenuPageVO> VO分页数据
	 */
	default PageResult<SysMenuPageVO> queryPage(PageParam pageParam, SysMenuQO qo) {
		IPage<SysMenu> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysMenu> wrapper = WrappersX.lambdaQueryX(SysMenu.class);
		this.selectPage(page, wrapper);
		IPage<SysMenuPageVO> voPage = page.convert(SysMenuConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	List<SysMenu> listByRoleCode(String roleCode);

	/**
	 * 根据指定的 id 更新 菜单权限（便于修改其 id）
	 * @param sysMenu 系统菜单
	 * @param originalId 原菜单ID
	 * @return 更新成功返回 true
	 */
	default boolean updateMenuAndId(Integer originalId, SysMenu sysMenu) {
		// @formatter:off
		LambdaUpdateWrapper<SysMenu> wrapper = Wrappers.lambdaUpdate(SysMenu.class)
				.set(SysMenu::getId, sysMenu.getId())
				.eq(SysMenu::getId, originalId);
		// @formatter:on
		int flag = this.update(sysMenu, wrapper);
		return SqlHelper.retBool(flag);
	}

	/**
	 * 根据指定的 parentId 找到对应的菜单，更新其 parentId
	 * @param originalParentId 原 parentId
	 * @param parentId 现 parentId
	 * @return 更新条数不为 0 时，返回 true
	 */
	default boolean updateParentId(Integer originalParentId, Integer parentId) {
		// @formatter:off
		LambdaUpdateWrapper<SysMenu> wrapper = Wrappers.lambdaUpdate(SysMenu.class)
				.set(SysMenu::getParentId, parentId)
				.eq(SysMenu::getParentId, originalParentId);
		// @formatter:on
		int flag = this.update(null, wrapper);
		return SqlHelper.retBool(flag);
	}

}
