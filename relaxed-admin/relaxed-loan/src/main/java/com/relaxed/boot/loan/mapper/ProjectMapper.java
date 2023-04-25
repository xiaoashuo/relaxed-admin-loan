package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.relaxed.boot.loan.converter.ProjectConverter;
import com.relaxed.boot.loan.model.entity.Project;
import com.relaxed.boot.loan.model.qo.ProjectQO;
import com.relaxed.boot.loan.model.vo.ProjectPageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;

/**
 * 产品配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface ProjectMapper extends ExtendMapper<Project> {

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param qo 查询参数
	 * @return PageResult<ProjectPageVO> VO分页数据
	 */
	default PageResult<ProjectPageVO> queryPage(PageParam pageParam, ProjectQO qo) {
		IPage<ProjectPageVO> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<Project> wrapper = WrappersX.lambdaQueryX(Project.class);
		wrapper.eqIfPresent(Project::getProjectId, qo.getProjectId());
		this.selectByPage(page, wrapper);

		return new PageResult<>(page.getRecords(), page.getTotal());
	}

	IPage<ProjectPageVO> selectByPage(IPage<ProjectPageVO> page,
			@Param(Constants.WRAPPER) Wrapper<Project> queryWrapper);

}
