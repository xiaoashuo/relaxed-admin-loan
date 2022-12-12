package com.relaxed.boot.biz.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.relaxed.boot.biz.system.mapper.SysOrganizationMapper;
import com.relaxed.boot.biz.system.service.SysOrganizationService;
import com.relaxed.boot.common.system.converter.SysOrganizationConverter;
import com.relaxed.boot.common.system.model.dto.OrganizationMoveChildParam;
import com.relaxed.boot.common.system.model.dto.SysOrganizationDTO;
import com.relaxed.boot.common.system.model.entity.SysOrganization;
import com.relaxed.boot.common.system.model.vo.SysOrganizationPageVO;
import com.relaxed.boot.common.system.model.qo.SysOrganizationQO;

import com.relaxed.boot.common.system.model.vo.SysOrganizationTree;
import com.relaxed.common.core.constants.GlobalConstants;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.core.util.tree.TreeUtils;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 组织架构
 *
 * @author Yakir 2022-11-23 17:56:28
 */
@Service
public class SysOrganizationServiceImpl extends ExtendServiceImpl<SysOrganizationMapper, SysOrganization>
		implements SysOrganizationService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysOrganizationPageVO> 分页数据
	 */
	@Override
	public PageResult<SysOrganizationPageVO> queryPage(PageParam pageParam, SysOrganizationQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	/**
	 * 返回组织架构的树形结构
	 * @param sysOrganizationQO 组织机构查询条件
	 * @return OrganizationTree
	 */
	@Override
	public List<SysOrganizationTree> listTree(SysOrganizationQO sysOrganizationQO) {
		List<SysOrganization> list = this.list();
		List<SysOrganizationTree> tree = TreeUtils.buildTree(list, GlobalConstants.TREE_ROOT_ID,
				SysOrganizationConverter.INSTANCE::poToTree);

		// 如果有名称的查询条件，则进行剪枝操作
		String name = sysOrganizationQO.getName();
		if (StrUtil.isNotEmpty(name)) {
			return TreeUtils.pruneTree(tree, node -> node.getName() != null && node.getName().contains(name));
		}

		return tree;
	}

	/**
	 * 创建一个新的组织机构
	 * @param sysOrganizationDTO 组织机构DTO
	 * @return boolean 创建成功/失败
	 */
	@Override
	public boolean create(SysOrganizationDTO sysOrganizationDTO) {
		sysOrganizationDTO.setId(null);
		SysOrganization sysOrganization = SysOrganizationConverter.INSTANCE.dtoToPo(sysOrganizationDTO);

		// 如果父级为根节点则直接设置深度和层级，否则根据父节点数据动态设置
		Integer parentId = sysOrganizationDTO.getParentId();
		// 填充层级和深度
		fillDepthAndHierarchy(sysOrganization, parentId);

		return SqlHelper.retBool(baseMapper.insert(sysOrganization));
	}

	/**
	 * 更新一个已有的组织机构
	 * @param sysOrganizationDTO 组织机构DTO
	 * @return boolean 更新成功/失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean update(SysOrganizationDTO sysOrganizationDTO) {
		// TODO 防止并发问题
		SysOrganization newSysOrganization = SysOrganizationConverter.INSTANCE.dtoToPo(sysOrganizationDTO);
		Integer organizationId = newSysOrganization.getId();
		SysOrganization originSysOrganization = baseMapper.selectById(organizationId);

		// 如果没有移动父节点，则直接更新
		Integer targetParentId = sysOrganizationDTO.getParentId();
		if (originSysOrganization.getParentId().equals(targetParentId)) {
			return SqlHelper.retBool(baseMapper.updateById(newSysOrganization));
		}

		// 移动了父节点，先判断不是选择自己作为父节点
		Assert.isFalse(targetParentId.equals(organizationId), "父节点不能是自己！");
		// 再判断是否是自己的子节点，根节点跳过判断
		if (!GlobalConstants.TREE_ROOT_ID.equals(targetParentId)) {
			SysOrganization targetParentOrganization = baseMapper.selectById(targetParentId);
			String[] targetParentHierarchy = targetParentOrganization.getHierarchy().split("-");
			if (ArrayUtil.contains(targetParentHierarchy, String.valueOf(organizationId))) {
				throw new BusinessException(BaseResultCode.LOGIC_CHECK_ERROR.getCode(), "父节点不能是自己的子节点！");
			}
		}

		// 填充目标层级和深度
		fillDepthAndHierarchy(newSysOrganization, targetParentId);
		// 更新其子节点的数据
		OrganizationMoveChildParam param = getOrganizationMoveChildParam(newSysOrganization, originSysOrganization);
		baseMapper.followMoveChildNode(param);
		// 更新组织节点信息
		return SqlHelper.retBool(baseMapper.updateById(newSysOrganization));
	}

	private OrganizationMoveChildParam getOrganizationMoveChildParam(SysOrganization newSysOrganization,
			SysOrganization originSysOrganization) {
		// 父组织 id
		Integer parentId = newSysOrganization.getId();
		// 父节点原来的层级
		String originParentHierarchy = originSysOrganization.getHierarchy();
		// 修改后的父节点层级
		String targetParentHierarchy = newSysOrganization.getHierarchy();
		// 父节点移动后的深度差
		int depthDiff = originSysOrganization.getDepth() - newSysOrganization.getDepth();

		OrganizationMoveChildParam param = new OrganizationMoveChildParam();
		param.setParentId(parentId);
		param.setOriginParentHierarchy(originParentHierarchy);
		param.setOriginParentHierarchyLengthPlusOne(originParentHierarchy.length() + 1);
		param.setTargetParentHierarchy(targetParentHierarchy);
		param.setDepthDiff(depthDiff);
		param.setGrandsonConditionalStatement(originParentHierarchy + "-" + parentId + "-%");
		return param;
	}

	/**
	 * 根据父级ID填充当前组织机构实体的深度和层级
	 * @param sysOrganization 组织机构实体
	 * @param parentId 父级ID
	 */
	private void fillDepthAndHierarchy(SysOrganization sysOrganization, Integer parentId) {
		if (GlobalConstants.TREE_ROOT_ID.equals(parentId)) {
			sysOrganization.setDepth(1);
			sysOrganization.setHierarchy(GlobalConstants.TREE_ROOT_ID.toString());
		}
		else {
			SysOrganization parentSysOrganization = baseMapper.selectById(parentId);
			Assert.notNull(parentSysOrganization, "不存在的父级组织机构！");

			sysOrganization.setDepth(parentSysOrganization.getDepth() + 1);
			sysOrganization.setHierarchy(parentSysOrganization.getHierarchy() + "-" + parentSysOrganization.getId());
		}
	}

}
