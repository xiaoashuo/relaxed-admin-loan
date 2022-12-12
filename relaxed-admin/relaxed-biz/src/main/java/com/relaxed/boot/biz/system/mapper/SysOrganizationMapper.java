package com.relaxed.boot.biz.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.relaxed.boot.common.system.converter.SysOrganizationConverter;
import com.relaxed.boot.common.system.model.dto.OrganizationMoveChildParam;
import com.relaxed.boot.common.system.model.entity.SysOrganization;
import com.relaxed.boot.common.system.model.qo.SysOrganizationQO;
import com.relaxed.boot.common.system.model.vo.SysOrganizationPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.apache.ibatis.annotations.Param;

/**
 * 组织架构
 *
 * @author Yakir 2022-11-23 17:56:28
 */
public interface SysOrganizationMapper extends ExtendMapper<SysOrganization> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<SysOrganizationPageVO> VO分页数据
	 */
	default PageResult<SysOrganizationPageVO> queryPage(PageParam pageParam, SysOrganizationQO qo) {
		IPage<SysOrganization> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<SysOrganization> wrapper = WrappersX.lambdaQueryX(SysOrganization.class);
		this.selectPage(page, wrapper);
		IPage<SysOrganizationPageVO> voPage = page.convert(SysOrganizationConverter.INSTANCE::poToPageVo);
		return new PageResult<>(voPage.getRecords(), voPage.getTotal());
	}

	/**
	 * 跟随父节点移动子节点
	 * @param param OrganizationMoveChildParam 跟随移动子节点的参数对象
	 */
	void followMoveChildNode(@Param("param") OrganizationMoveChildParam param);

}
