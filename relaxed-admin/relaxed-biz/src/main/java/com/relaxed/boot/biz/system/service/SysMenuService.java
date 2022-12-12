package com.relaxed.boot.biz.system.service;

import com.relaxed.boot.common.system.model.dto.SysMenuUpdateDTO;
import com.relaxed.boot.common.system.model.entity.SysMenu;
import com.relaxed.boot.common.system.model.vo.SysMenuPageVO;
import com.relaxed.boot.common.system.model.qo.SysMenuQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

import java.util.List;

/**
 * 菜单权限 编号规则目录-菜单-按钮 eg: 10-01-01=100101
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysMenuService extends ExtendService<SysMenu> {

	/**
	 * 根据QueryObeject查询分页数据
	 * @param pageParam 分页参数
	 * @param qo 查询参数对象
	 * @return PageResult&lt;SysMenuPageVO&gt; 分页数据
	 */
	PageResult<SysMenuPageVO> queryPage(PageParam pageParam, SysMenuQO qo);

	List<SysMenu> listByRoleCode(String roleCode);

	boolean updateMenuAndId(Integer originalId, SysMenu sysMenu);

	void updateParentId(Integer originalId, Integer menuId);

}
