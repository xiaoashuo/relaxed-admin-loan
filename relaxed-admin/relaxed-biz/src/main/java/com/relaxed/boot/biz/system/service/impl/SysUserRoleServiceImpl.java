package com.relaxed.boot.biz.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.entity.SysUserRole;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageVO;
import com.relaxed.boot.common.system.model.qo.SysUserRoleQO;
import com.relaxed.boot.biz.system.mapper.SysUserRoleMapper;
import com.relaxed.boot.biz.system.service.SysUserRoleService;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色表
 *
 * @author yakir 2022-11-11 10:27:36
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl extends ExtendServiceImpl<SysUserRoleMapper, SysUserRole>
		implements SysUserRoleService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysUserRolePageVO> 分页数据
	 */
	@Override
	public PageResult<SysUserRolePageVO> queryPage(PageParam pageParam, SysUserRoleQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	/**
	 * 通过用户ID 获取用户所有角色ID
	 * @param userId 用户ID
	 * @return 用户拥有的角色集合
	 */
	@Override
	public List<SysRole> listRoles(Integer userId) {
		return baseMapper.listRoleByUserId(userId);
	}

	/**
	 * 更新用户关联关系
	 * @param userId 用户ID
	 * @param roleCodes 角色标识集合
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateUserRoles(@NonNull Integer userId, @NonNull List<String> roleCodes) {
		// 是否存在用户角色绑定关系，存在则先清空
		boolean existsRoleBind = baseMapper.existsRoleBind(userId, null);
		if (existsRoleBind) {
			boolean deleteSuccess = baseMapper.deleteByUserId(userId);
			Assert.isTrue(deleteSuccess, () -> {
				log.error("[updateUserRoles] 删除用户角色关联关系失败，userId：{}，roleCodes：{}", userId, roleCodes);
				return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "删除用户角色关联关系失败");
			});
		}

		// 没有的新授权的角色直接返回
		if (CollectionUtil.isEmpty(roleCodes)) {
			return true;
		}

		// 保存新的用户角色关联关系
		return addUserRoles(userId, roleCodes);
	}

	/**
	 * 插入用户角色关联关系
	 * @param userId 用户ID
	 * @param roleCodes 角色标识集合
	 * @return boolean
	 */
	@Override
	public boolean addUserRoles(@NonNull Integer userId, @NonNull List<String> roleCodes) {
		List<SysUserRole> list = prodSysUserRoles(userId, roleCodes);
		// 批量插入
		boolean insertSuccess = SqlHelper.retBool(baseMapper.insertBatchSomeColumn(list));
		Assert.isTrue(insertSuccess, () -> {
			log.error("[addUserRoles] 插入用户角色关联关系失败，userId：{}，roleCodes：{}", userId, roleCodes);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "插入用户角色关联关系失败");
		});
		return insertSuccess;
	}

	/**
	 * 根据用户ID 和 角色Code 生成SysUserRole实体集合
	 * @param userId 用户ID
	 * @param roleCodes 角色标识集合
	 * @return List<SysUserRole>
	 */
	private List<SysUserRole> prodSysUserRoles(Integer userId, List<String> roleCodes) {
		// 转换为 SysUserRole 实体集合
		List<SysUserRole> list = new ArrayList<>();
		for (String roleCode : roleCodes) {
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleCode(roleCode);
			list.add(sysUserRole);
		}
		return list;
	}

	@Override
	public boolean unbindRoleUser(Integer userId, String roleCode) {
		// 不存在则不需要进行删除，直接返回true
		return !baseMapper.existsRoleBind(userId, roleCode) || baseMapper.deleteUserRole(userId, roleCode);
	}

}
