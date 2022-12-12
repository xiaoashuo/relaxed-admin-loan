package com.relaxed.boot.biz.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.relaxed.boot.common.system.converter.SysUserConverter;
import com.relaxed.boot.common.system.model.dto.SysUserDTO;
import com.relaxed.boot.common.system.model.dto.SysUserScope;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.boot.common.system.model.qo.SysUserRoleQO;
import com.relaxed.boot.common.system.model.vo.SysUserPageVO;
import com.relaxed.boot.common.system.model.qo.SysUserQO;
import com.relaxed.boot.biz.system.mapper.SysUserMapper;
import com.relaxed.boot.biz.system.service.SysUserService;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageDetailVO;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户
 *
 * @author yakir 2022-11-11 10:27:36
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SysUserServiceImpl extends ExtendServiceImpl<SysUserMapper, SysUser> implements SysUserService {




	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysUserPageVO> 分页数据
	 */
	@Override
	public PageResult<SysUserPageVO> queryPage(PageParam pageParam, SysUserQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public SysUser getByUsername(String username) {
		return getBaseMapper().selectOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getUsername, username));
	}

	@Override
	public SysUser getByMobileNo(String mobileNo) {
		return getBaseMapper().selectOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getPhone, mobileNo));
	}

	@Override
	public PageResult<SysUserRolePageDetailVO> queryRelatedSysUserPageByRole(PageParam pageParam,
			SysUserRoleQO sysUserRoleQO) {
		return baseMapper.queryRelatedSysUserPageByRole(pageParam, sysUserRoleQO);
	}

	@Override
	public boolean updateUserStatus(Long userId, Integer status) {
		boolean success = SqlHelper.retBool(baseMapper.update(null,new LambdaUpdateWrapper<SysUser>()
				.eq(SysUser::getUserId, userId)
				.set(SysUser::getStatus, status)
		));
		Assert.isTrue(success,()->{
			log.error("[updateUserStatus] 修改用户状态失败，userId：{}", userId);
			return new BusinessException(BaseResultCode.UPDATE_DATABASE_ERROR.getCode(), "修改用户状态失败");
		});
		return success;
	}
}
