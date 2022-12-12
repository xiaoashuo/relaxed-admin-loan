package com.relaxed.boot.biz.system.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.common.system.constants.SysConstant;
import com.relaxed.boot.common.system.converter.SysMenuConverter;
import com.relaxed.boot.common.system.model.dto.SysMenuUpdateDTO;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.vo.SysMenuPageVO;
import com.relaxed.boot.common.system.model.qo.SysMenuQO;
import com.relaxed.boot.biz.system.mapper.SysMenuMapper;
import com.relaxed.boot.biz.system.service.SysMenuService;
import com.relaxed.boot.common.system.utils.BeanUtil;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.core.util.tree.TreeUtils;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.common.model.result.R;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Service
public class SysMenuServiceImpl extends ExtendServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysMenuPageVO> 分页数据
	 */
	@Override
	public PageResult<SysMenuPageVO> queryPage(PageParam pageParam, SysMenuQO qo) {
		List<SysMenu> sysMenuList = baseMapper.selectList(Wrappers.emptyWrapper());
		Function<SysMenu, SysMenuPageVO> convert = SysMenuConverter.INSTANCE::poToPageVo;

		if (BeanUtil.isEmpty(qo)) {
			List<SysMenuPageVO> sysMenuPageVOList = TreeUtils.buildTree(sysMenuList, SysConstant.MENU_ROOT_ID, convert);
			return new PageResult<>(sysMenuPageVOList, sysMenuPageVOList.size());
		}
		LambdaQueryWrapperX<SysMenu> wrapper = WrappersX.lambdaQueryX(SysMenu.class)
				.eqIfPresent(SysMenu::getId, qo.getId()).eqIfPresent(SysMenu::getParentId, qo.getParentId())
				.eqIfPresent(SysMenu::getTitle, qo.getTitle());
		SysMenu sysMenu = baseMapper.selectOne(wrapper);
		if (sysMenu == null) {
			List<SysMenuPageVO> sysMenuPageVOList = TreeUtils.buildTree(sysMenuList, SysConstant.MENU_ROOT_ID, convert);
			return new PageResult<>(sysMenuPageVOList, sysMenuPageVOList.size());
		}
		Integer parentId = sysMenu.getId();
		List<SysMenuPageVO> sysMenuPageVOList = TreeUtils.buildTree(sysMenuList, parentId, convert);
		SysMenuPageVO parentPageVO = convert.apply(sysMenu);
		parentPageVO.setChildren(sysMenuPageVOList);
		sysMenuPageVOList = ListUtil.toList(parentPageVO);
		return new PageResult<>(sysMenuPageVOList, sysMenuPageVOList.size());
	}

	@Override
	public List<SysMenu> listByRoleCode(String roleCode) {
		return baseMapper.listByRoleCode(roleCode);
	}

	@Override
	public boolean updateMenuAndId(Integer originalId, SysMenu sysMenu) {
		return baseMapper.updateMenuAndId(originalId, sysMenu);
	}

	@Override
	public void updateParentId(Integer originalId, Integer menuId) {
		baseMapper.updateParentId(originalId, menuId);
	}

}
