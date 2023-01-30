package com.relaxed.boot.loan.service;

import com.relaxed.boot.loan.model.dto.ProjectTemplateDTO;
import com.relaxed.boot.loan.model.dto.ProjectTemplateSignReqDTO;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.vo.ProjectTemplatePageVO;
import com.relaxed.boot.loan.model.qo.ProjectTemplateQO;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.ExtendService;

/**
 * 项目模板配置表
 *
 * @author yakir 2023-01-02 09:55:28
 */
public interface ProjectTemplateService extends ExtendService<ProjectTemplate> {

    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult&lt;ProjectTemplatePageVO&gt; 分页数据
    */
    PageResult<ProjectTemplatePageVO> queryPage(PageParam pageParam, ProjectTemplateQO qo);

    ProjectTemplateDTO detail(Integer projectTemplateId);

    boolean saveSignInfo(ProjectTemplateSignReqDTO projectTemplateSignReqDTO);

    ProjectTemplate getByPidAndFileType(Integer projectId, Integer fileType);
}
