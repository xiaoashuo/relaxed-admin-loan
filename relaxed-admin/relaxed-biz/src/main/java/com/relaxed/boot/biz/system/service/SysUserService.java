package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.dto.SysUserDTO;
import com.relaxed.boot.common.system.model.dto.SysUserScope;
import com.relaxed.boot.common.system.model.entity.SysUser;
import com.relaxed.boot.common.system.model.qo.SysUserRoleQO;
import com.relaxed.boot.common.system.model.vo.SysUserPageVO;
import com.relaxed.boot.common.system.model.qo.SysUserQO;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageDetailVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 系统用户
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface SysUserService extends ExtendService<SysUser> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysUserPageVO&gt; 分页数据
	 */
	PageResult<SysUserPageVO> queryPage(PageParam pageParam, SysUserQO qo);

	SysUser getByUsername(String username);

	SysUser getByMobileNo(String mobileNo);

	/**
	 * 分页查询关联用户 根据角色
	 * @author yakir
	 * @date 2022/11/19 15:08
	 * @param pageParam
	 * @param sysUserRoleQO
	 * @return com.relaxed.common.model.domain.PageResult<com.relaxed.boot.common.system.model.vo.SysUserPageVO>
	 */
	PageResult<SysUserRolePageDetailVO> queryRelatedSysUserPageByRole(PageParam pageParam, SysUserRoleQO sysUserRoleQO);

	/**
	 * 更新用户状态
	 * @author yakir
	 * @date 2022/11/27 15:10
	 * @param userId
	 * @param status
	 * @return boolean
	 */
	boolean updateUserStatus(Long userId, Integer status);

}
