package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.common.system.converter.SysRoleConverter;
import com.relaxed.boot.common.system.model.entity.SysRole;
import com.relaxed.boot.common.system.model.qo.SysRoleQO;
import com.relaxed.boot.common.system.model.vo.SysRolePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;

import java.util.List;

/**
 * 系统角色
 *
 * @author yakir 2022-11-11 10:27:53
 */
public interface SysRoleMapper extends ExtendMapper<SysRole> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysRolePageVO> VO分页数据
	 */
	default PageResult<SysRolePageVO> queryPage(PageParam pageParam, SysRoleQO qo) {
		IPage<SysRole> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysRole> wrapper = WrappersX.lambdaQueryX(SysRole.class);
		wrapper.eqIfPresent(SysRole::getName, qo.getName());
		wrapper.eqIfPresent(SysRole::getCode, qo.getCode());
		this.selectPage(page, wrapper);
		IPage<SysRolePageVO> voPage = page.convert(SysRoleConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	List<SysRole> listRoleByUserId(Integer userId);

	List<SelectData<Void>> listSelectData();

}
