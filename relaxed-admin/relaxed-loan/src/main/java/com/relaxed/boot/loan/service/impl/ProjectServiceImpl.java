package com.relaxed.boot.loan.service.impl;

import com.relaxed.boot.loan.model.entity.Project;
import com.relaxed.boot.loan.model.vo.ProjectPageVO;
import com.relaxed.boot.loan.model.qo.ProjectQO;
import com.relaxed.boot.loan.mapper.ProjectMapper;
import com.relaxed.boot.loan.service.ProjectService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 产品配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@Service
public class ProjectServiceImpl extends ExtendServiceImpl<ProjectMapper, Project> implements ProjectService {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<ProjectPageVO> 分页数据
    */
    @Override
    public PageResult<ProjectPageVO> queryPage(PageParam pageParam, ProjectQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

}
