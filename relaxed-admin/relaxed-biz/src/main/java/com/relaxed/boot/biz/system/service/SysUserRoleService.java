package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.entity.SysUserRole;
import com.relaxed.boot.common.system.model.vo.SysUserRolePageVO;
import com.relaxed.boot.common.system.model.qo.SysUserRoleQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 用户角色表
 *
 * @author yakir 2022-11-11 10:27:36
 */
public interface SysUserRoleService extends ExtendService<SysUserRole> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysUserRolePageVO&gt; 分页数据
	 */
	PageResult<SysUserRolePageVO> queryPage(PageParam pageParam, SysUserRoleQO qo);

	/**
	 * 通过用户ID，查询角色列表
	 * @param userId 用户ID
	 * @return List<SysRole>
	 */
	List<SysRole> listRoles(Integer userId);

	/**
	 * 添加用户角色关联关系
	 * @param userId 用户ID
	 * @param roleCodes 角色标识集合
	 * @return 插入是否成功
	 */
	boolean addUserRoles(Integer userId, List<String> roleCodes);

	/**
	 * 更新用户关联关系
	 * @param userId 用户ID
	 * @param roleCodes 角色标识集合
	 * @return boolean
	 */
	boolean updateUserRoles(Integer userId, List<String> roleCodes);

	/**
	 * 解绑用户信息
	 * @author yakir
	 * @date 2022/11/19 16:48
	 * @param userId
	 * @param roleCode
	 * @return boolean
	 */
	boolean unbindRoleUser(Integer userId, String roleCode);

}
