package com.relaxed.boot.common.system.converter;

import com.relaxed.boot.common.system.model.dto.SysOrganizationDTO;
import com.relaxed.boot.common.system.model.entity.SysOrganization;
import com.relaxed.boot.common.system.model.vo.SysOrganizationPageVO;
import com.relaxed.boot.common.system.model.vo.SysOrganizationTree;
import com.relaxed.boot.common.system.model.vo.SysOrganizationVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 组织架构模型转换器
 *
 * @author Yakir 2022-11-23 17:56:28
 */
@Mapper
public interface SysOrganizationConverter {

	SysOrganizationConverter INSTANCE = Mappers.getMapper(SysOrganizationConverter.class);

	/**
	 * PO 转 PageVO
	 * @param sysOrganization 组织架构
	 * @return SysOrganizationPageVO 组织架构PageVO
	 */
	SysOrganizationPageVO poToPageVo(SysOrganization sysOrganization);

	/**
	 * po -> vo
	 * @param sysOrganization
	 * @return
	 */
	SysOrganizationVO poToVo(SysOrganization sysOrganization);

	/**
	 * po -> tree
	 * @param sysOrganization
	 * @return
	 */
	SysOrganizationTree poToTree(SysOrganization sysOrganization);

	/**
	 * dto -> po
	 * @param sysOrganizationDTO
	 * @return
	 */
	SysOrganization dtoToPo(SysOrganizationDTO sysOrganizationDTO);

}
