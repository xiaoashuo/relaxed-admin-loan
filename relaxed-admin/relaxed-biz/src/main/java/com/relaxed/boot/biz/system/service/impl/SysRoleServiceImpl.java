package com.relaxed.boot.biz.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.common.system.converter.SysRoleConverter;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.vo.SysRolePageVO;
import com.relaxed.boot.common.system.model.qo.SysRoleQO;
import com.relaxed.boot.biz.system.mapper.SysRoleMapper;
import com.relaxed.boot.biz.system.service.SysRoleService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统角色
 *
 * @author yakir 2022-11-11 10:27:53
 */
@Service
public class SysRoleServiceImpl extends ExtendServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult<SysRolePageVO> 分页数据
	 */
	@Override
	public PageResult<SysRolePageVO> queryPage(PageParam pageParam, SysRoleQO qo) {
		return baseMapper.queryPage(pageParam, qo);
	}

	@Override
	public List<SysRole> listRoles(Integer userId) {
		return baseMapper.listRoleByUserId(userId);
	}

	@Override
	public List<SelectData<Void>> listSelectData() {
		return baseMapper.listSelectData();
	}

	@Override
	public SysRolePageVO getSysRoleByRoleId(Integer roleId) {
		SysRole sysRole = baseMapper.selectOne(Wrappers.lambdaQuery(SysRole.class).eq(SysRole::getId, roleId));
		return SysRoleConverter.INSTANCE.poToPageVo(sysRole);
	}

}
