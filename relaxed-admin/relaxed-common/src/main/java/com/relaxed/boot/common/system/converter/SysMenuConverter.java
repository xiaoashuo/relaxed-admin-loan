package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.dto.SysMenuCreateDTO;
import com.relaxed.boot.common.system.model.dto.SysMenuUpdateDTO;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.qo.SysMenuGrantVO;
import com.relaxed.boot.common.system.model.vo.RouterVO;
import com.relaxed.boot.common.system.model.vo.SysMenuPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101模型转换器
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Mapper
public interface SysMenuConverter {

	SysMenuConverter INSTANCE = Mappers.getMapper(SysMenuConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysMenu 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
	 * @return SysMenuPageVO 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101PageVO
	 */
	SysMenuPageVO poToPageVo(SysMenu sysMenu);

	/**
	 * PO -> routerVo
	 * @param sysMenu
	 * @return
	 */
	RouterVO poToRouterVo(SysMenu sysMenu);

	/**
	 * po -> grant vo
	 * @param sysMenu
	 * @return
	 */
	SysMenuGrantVO poToGrantVo(SysMenu sysMenu);

	/**
	 * createDto 转 Po
	 * @param sysMenuCreateDTO 菜单新建对象
	 * @return SysMenu 菜单权限的持久化对象
	 */
	SysMenu createDtoToPo(SysMenuCreateDTO sysMenuCreateDTO);

	/**
	 * updateDto 转 Po
	 * @param sysMenuUpdateDTO 菜单修改对象
	 * @return SysMenu 菜单权限的持久化对象
	 */
	SysMenu updateDtoToPo(SysMenuUpdateDTO sysMenuUpdateDTO);

}
