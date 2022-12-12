package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.relaxed.boot.common.system.converter.SysUserRoleConverter;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.entity.SysUserRole;
import com.relaxed.boot.common.system.model.qo.SysUserRoleQO;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

import java.util.List;

/**
 * 用户角色表
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface SysUserRoleMapper extends ExtendMapper<SysUserRole> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysUserRolePageVO> VO分页数据
	 */
	default PageResult<SysUserRolePageVO> queryPage(PageParam pageParam, SysUserRoleQO qo) {
		IPage<SysUserRole> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysUserRole> wrapper = WrappersX.lambdaQueryX(SysUserRole.class);
		this.selectPage(page, wrapper);
		IPage<SysUserRolePageVO> voPage = page.convert(SysUserRoleConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	/**
	 * 通过用户ID，查询角色
	 * @param userId 用户ID
	 * @return 用户拥有的角色集合
	 */
	List<SysRole> listRoleByUserId(Integer userId);

	/**
	 * 用户是否存在角色绑定关系
	 * @param userId 用户ID
	 * @param roleCode 角色标识，可为空
	 * @return 存在：true
	 */
	default boolean existsRoleBind(Integer userId, String roleCode) {
		Long num = this.selectCount(WrappersX.lambdaQueryX(SysUserRole.class).eq(SysUserRole::getUserId, userId)
				.eqIfPresent(SysUserRole::getRoleCode, roleCode));
		return SqlHelper.retBool(num);
	}

	/**
	 * 删除用户关联关系
	 * @param userId 用户ID
	 * @return boolean 删除是否成功
	 */
	default boolean deleteByUserId(Integer userId) {
		int i = this.delete(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, userId));
		return SqlHelper.retBool(i);
	}

	/**
	 * 删除角色和用户关系
	 * @param userId 用户ID
	 * @param roleCode 角色标识
	 * @return 删除成功：true
	 */
	default boolean deleteUserRole(Integer userId, String roleCode) {
		int i = this.delete(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, userId)
				.eq(SysUserRole::getRoleCode, roleCode));
		return SqlHelper.retBool(i);
	}

}
