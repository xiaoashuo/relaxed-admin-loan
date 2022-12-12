package com.relaxed.boot.web.system.manage;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.relaxed.boot.auth.constants.UserConstant;
import com.relaxed.boot.auth.domain.SysUserDetails;
import com.relaxed.boot.biz.system.service.SysMenuService;
import com.relaxed.boot.biz.system.service.SysRoleMenuService;
import com.relaxed.boot.common.system.constants.SysConstant;
import com.relaxed.boot.common.system.converter.SysMenuConverter;
import com.relaxed.boot.common.system.enums.SysMenuType;
import com.relaxed.boot.common.system.model.dto.SysMenuCreateDTO;
import com.relaxed.boot.common.system.model.dto.SysMenuUpdateDTO;
import com.relaxed.boot.common.system.model.vo.RouterVO;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.core.util.tree.TreeUtils;
import com.relaxed.common.model.result.BaseResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yakir
 * @Topic SysMenuManage
 * @Description
 * @date 2022/11/11 14:06
 * @Version 1.0
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class SysMenuManage {

	private final SysMenuService sysMenuService;

	private final SysRoleMenuService sysRoleMenuService;

	public List<RouterVO> routerList(SysUserDetails sysUserDetails) {
		Map<String, Object> attributes = sysUserDetails.getAttributes();
		Object rolesObject = attributes.get(UserConstant.ROLE_CODES);
		if (!(rolesObject instanceof Collection)) {
			return new ArrayList<>();
		}
		Collection<String> roleCodes = (Collection<String>) rolesObject;
		if (CollectionUtil.isEmpty(roleCodes)) {
			return new ArrayList<>();
		}
		// 获取符合条件的权限
		Set<SysMenu> all = new HashSet<>();
		roleCodes.forEach(roleCode -> all.addAll(sysMenuService.listByRoleCode(roleCode)));
		// 筛选出菜单
		List<SysMenu> sysMenuList = all.stream().filter(menuVo -> SysMenuType.BUTTON.getValue() != menuVo.getType())
				.sorted(Comparator.comparingInt(SysMenu::getSort)).collect(Collectors.toList());
		List<RouterVO> menuVOList = TreeUtils.buildTree(sysMenuList, SysConstant.MENU_ROOT_ID,
				SysMenuConverter.INSTANCE::poToRouterVo);
		return menuVOList;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean create(SysMenuCreateDTO sysMenuCreateDTO) {
		SysMenu sysMenu = SysMenuConverter.INSTANCE.createDtoToPo(sysMenuCreateDTO);
		Integer menuId = sysMenu.getId();
		if (menuId != null) {
			SysMenu existingMenu = sysMenuService.getById(menuId);
			if (existingMenu != null) {
				String errorMessage = String.format("ID [%s] 已被菜单 [%s] 使用，请更换其他菜单ID", menuId, existingMenu.getTitle());
				throw new BusinessException(BaseResultCode.LOGIC_CHECK_ERROR.getCode(), errorMessage);
			}
		}
		boolean saveSuccess = sysMenuService.save(sysMenu);
		Assert.isTrue(saveSuccess, () -> {
			log.error("[create] 创建菜单失败，sysMenuCreateDTO: {}", sysMenuCreateDTO);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "创建菜单失败");
		});

		return saveSuccess;
	}

	@Transactional(rollbackFor = Exception.class)
	public void update(SysMenuUpdateDTO sysMenuUpdateDTO) {
		// 原来的菜单 Id
		Integer originalId = sysMenuUpdateDTO.getOriginalId();
		SysMenu sysMenu = SysMenuConverter.INSTANCE.updateDtoToPo(sysMenuUpdateDTO);

		// 更新菜单信息
		boolean updateSuccess = sysMenuService.updateMenuAndId(originalId, sysMenu);
		Assert.isTrue(updateSuccess, () -> {
			log.error("[update] 更新菜单权限时，sql 执行异常，originalId：{}，sysMenu：{}", originalId, sysMenu);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "更新菜单权限时，sql 执行异常");
		});

		// 如果未修改过 菜单id 直接返回
		Integer menuId = sysMenuUpdateDTO.getId();
		if (originalId.equals(menuId)) {
			return;
		}

		// 修改过菜单id，则需要对应修改角色菜单的关联表信息，这里不需要 check，因为可能没有授权过该菜单，所以返回值为 0
		sysRoleMenuService.updateMenuId(originalId, menuId);
		// 更新子菜单的 parentId
		sysMenuService.updateParentId(originalId, menuId);
	}

}
