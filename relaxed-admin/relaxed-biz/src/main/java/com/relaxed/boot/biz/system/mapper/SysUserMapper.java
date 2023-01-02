package com.relaxed.boot.biz.system.mapper;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.relaxed.boot.common.system.converter.SysUserConverter;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.boot.common.system.model.qo.SysUserQO;
import com.relaxed.boot.common.system.model.qo.SysUserRoleQO;
import com.relaxed.boot.common.system.model.vo.SysUserPageVO;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageDetailVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaAliasQueryWrapperX;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.conditions.query.OtherTableColumnAliasFunction;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 系统用户
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface SysUserMapper extends ExtendMapper<SysUser> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysUserPageVO> VO分页数据
	 */
	default PageResult<SysUserPageVO> queryPage(PageParam pageParam, SysUserQO qo) {
		IPage<SysUser> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysUser> wrapper = WrappersX.lambdaQueryX(SysUser.class);
		wrapper.eqIfPresent(SysUser::getNickname, qo.getNickname());
		wrapper.eqIfPresent(SysUser::getUsername, qo.getUsername());
		wrapper.eqIfPresent(SysUser::getPhone, qo.getPhone());
		wrapper.eqIfPresent(SysUser::getStatus, qo.getStatus());
		wrapper.eqIfPresent(SysUser::getOrganizationId, qo.getOrganizationId());
		Map<String, Object> params = qo.getParams();
		if (MapUtil.isNotEmpty(params)) {
			wrapper.geIfPresent(SysUser::getCreatedTime, params.get("beginCreatedTime"));
			wrapper.leIfPresent(SysUser::getCreatedTime, params.get("endCreatedTime"));
		}

		this.selectPage(page, wrapper);
		IPage<SysUserPageVO> voPage = page.convert(SysUserConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	default PageResult<SysUserRolePageDetailVO> queryRelatedSysUserPageByRole(PageParam pageParam, SysUserRoleQO qo) {
		IPage<SysUserRolePageDetailVO> page = this.prodPage(pageParam);
		LambdaAliasQueryWrapperX<SysUser> wrapper = WrappersX.lambdaAliasQueryX(SysUser.class);
		wrapper.eq(OtherTableColumnAliasFunction.joinTableAliasColumn("ur.role_code"), qo.getRoleCode());
		this.queryRelatedSysUserPage(page, wrapper);
		return new PageResult<>(page.getRecords(), page.getTotal());
	}

	IPage<SysUserRolePageDetailVO> queryRelatedSysUserPage(IPage<SysUserRolePageDetailVO> page,
			@Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);

}
