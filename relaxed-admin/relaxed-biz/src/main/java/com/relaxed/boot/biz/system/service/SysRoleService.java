package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.vo.SysRolePageVO;
import com.relaxed.boot.common.system.model.qo.SysRoleQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 系统角色
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysRoleService extends ExtendService<SysRole> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysRolePageVO&gt; 分页数据
	 */
	PageResult<SysRolePageVO> queryPage(PageParam pageParam, SysRoleQO qo);

	List<SysRole> listRoles(Integer userId);

	List<SelectData<Void>> listSelectData();

	SysRolePageVO getSysRoleByRoleId(Integer roleId);

}
