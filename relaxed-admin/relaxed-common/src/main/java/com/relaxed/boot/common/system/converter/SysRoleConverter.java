package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.vo.SysRolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统角色模型转换器
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Mapper
public interface SysRoleConverter {

	SysRoleConverter INSTANCE = Mappers.getMapper(SysRoleConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysRole 系统角色
	 * @return SysRolePageVO 系统角色PageVO
	 */
	SysRolePageVO poToPageVo(SysRole sysRole);

}
