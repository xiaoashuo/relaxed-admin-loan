package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.dto.SysUserDTO;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.boot.common.system.model.vo.SysUserPageVO;
import com.relaxed.boot.common.system.model.vo.SysUserProfileVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 系统用户模型转换器
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Mapper
public interface SysUserConverter {

	SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysUser 系统用户
	 * @return SysUserPageVO 系统用户PageVO
	 */
	SysUserPageVO poToPageVo(SysUser sysUser);

	/**
	 * 转换DTO 为 PO
	 * @param sysUserDTO 系统用户DTO
	 * @return SysUser 系统用户
	 */
	// @Mapping(target = "password", ignore = true)
	SysUser dtoToPo(SysUserDTO sysUserDTO);

	/**
	 * po -> profile Vo
	 * @param sysUser
	 * @return
	 */
	SysUserProfileVO poToProfileVO(SysUser sysUser);

}
