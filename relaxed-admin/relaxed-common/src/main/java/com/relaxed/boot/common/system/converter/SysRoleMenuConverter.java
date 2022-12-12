package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.SysRoleMenu;
import com.relaxed.boot.common.system.model.vo.SysRoleMenuPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色菜单表模型转换器
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Mapper
public interface SysRoleMenuConverter {

	SysRoleMenuConverter INSTANCE = Mappers.getMapper(SysRoleMenuConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysRoleMenu 角色菜单表
	 * @return SysRoleMenuPageVO 角色菜单表PageVO
	 */
	SysRoleMenuPageVO poToPageVo(SysRoleMenu sysRoleMenu);

}
