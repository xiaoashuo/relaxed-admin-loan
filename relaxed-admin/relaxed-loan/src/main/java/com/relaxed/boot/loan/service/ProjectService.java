package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.entity.Project;
import com.relaxed.boot.loan.model.vo.ProjectPageVO;
import com.relaxed.boot.loan.model.qo.ProjectQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 产品配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
public interface ProjectService extends ExtendService<Project> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;ProjectPageVO&gt; 分页数据
    */
    PageResult<ProjectPageVO> queryPage(PageParam pageParam, ProjectQO qo);

}