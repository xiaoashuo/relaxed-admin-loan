package com.relaxed.boot.loan.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.relaxed.boot.loan.converter.ProjectTemplateConverter;
import com.relaxed.boot.loan.model.entity.Project;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.qo.ProjectTemplateQO;
import com.relaxed.boot.loan.model.vo.ProjectPageVO;
import com.relaxed.boot.loan.model.vo.ProjectTemplatePageVO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.conditions.query.LambdaQueryWrapperX;
import com.relaxed.extend.mybatis.plus.mapper.ExtendMapper;
import com.relaxed.extend.mybatis.plus.toolkit.WrappersX;
import org.apache.ibatis.annotations.Param;

/**
 * 项目模板配置表
 *
 * @author yakir 2023-01-02 09:55:28
 */
public interface ProjectTemplateMapper extends ExtendMapper<ProjectTemplate> {

    /**
    * 分页查询
    * @param pageParam 分页参数
    * @param qo 查询参数
    * @return PageResult<ProjectTemplatePageVO> VO分页数据
    */
   default PageResult<ProjectTemplatePageVO> queryPage(PageParam pageParam, ProjectTemplateQO qo){
		IPage<ProjectTemplatePageVO> page = this.prodPage(pageParam);
		LambdaQueryWrapperX<ProjectTemplate> wrapper = WrappersX.lambdaQueryX(ProjectTemplate.class);
		this.selectByPage(page, wrapper);
		return new PageResult<>(page.getRecords(), page.getTotal());
   }
	IPage<ProjectTemplatePageVO> selectByPage(IPage<ProjectTemplatePageVO> page,
									  @Param(Constants.WRAPPER) Wrapper<ProjectTemplate> queryWrapper);

}