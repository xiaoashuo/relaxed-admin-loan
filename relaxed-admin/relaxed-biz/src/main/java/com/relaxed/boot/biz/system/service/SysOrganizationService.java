package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.dto.SysOrganizationDTO;
import com.relaxed.boot.common.system.model.entity.SysOrganization;
import com.relaxed.boot.common.system.model.vo.SysOrganizationPageVO;
import com.relaxed.boot.common.system.model.qo.SysOrganizationQO;
import com.relaxed.boot.common.system.model.vo.SysOrganizationTree;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 组织架构
 *
 * @author Yakir 2022-11-23 17:56:28
 */
public interface SysOrganizationService extends ExtendService<SysOrganization> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysOrganizationPageVO&gt; 分页数据
	 */
	PageResult<SysOrganizationPageVO> queryPage(PageParam pageParam, SysOrganizationQO qo);

	/**
	 * 返回组织架构的树形结构
	 * @param sysOrganizationQO 组织机构查询条件
	 * @return OrganizationTree
	 */
	List<SysOrganizationTree> listTree(SysOrganizationQO sysOrganizationQO);

	/**
	 * 创建一个新的组织机构
	 * @param sysOrganizationDTO 组织机构DTO
	 * @return boolean 创建成功/失败
	 */
	boolean create(SysOrganizationDTO sysOrganizationDTO);

	/**
	 * 更新一个已有的组织机构
	 * @param sysOrganizationDTO 组织机构DTO
	 * @return boolean 更新成功/失败
	 */
	boolean update(SysOrganizationDTO sysOrganizationDTO);

}
