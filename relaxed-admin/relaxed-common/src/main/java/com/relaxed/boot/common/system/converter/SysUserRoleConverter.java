package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.entity.SysUserRole;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户角色表模型转换器
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Mapper
public interface SysUserRoleConverter {

	SysUserRoleConverter INSTANCE = Mappers.getMapper(SysUserRoleConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysUserRole 用户角色表
	 * @return SysUserRolePageVO 用户角色表PageVO
	 */
	SysUserRolePageVO poToPageVo(SysUserRole sysUserRole);

}
