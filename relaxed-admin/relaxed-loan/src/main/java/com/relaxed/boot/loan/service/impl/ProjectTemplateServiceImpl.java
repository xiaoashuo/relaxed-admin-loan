package com.relaxed.boot.loan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxed.boot.biz.system.service.SysDictItemService;
import com.relaxed.boot.common.system.enums.SysDictKey;
import com.relaxed.boot.common.system.model.entity.SysDictItem;
import com.relaxed.boot.loan.converter.ProjectTemplateConverter;
import com.relaxed.boot.loan.model.dto.ProjectTemplateDTO;
import com.relaxed.boot.loan.model.dto.ProjectTemplateSignReqDTO;
import com.relaxed.boot.loan.model.entity.Project;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.vo.ProjectTemplatePageVO;
import com.relaxed.boot.loan.model.qo.ProjectTemplateQO;
import com.relaxed.boot.loan.mapper.ProjectTemplateMapper;
import com.relaxed.boot.loan.service.ProjectService;
import com.relaxed.boot.loan.service.ProjectTemplateService;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.extend.mybatis.plus.service.impl.ExtendServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 项目模板配置表
 *
 * @author yakir 2023-01-02 09:55:28
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class ProjectTemplateServiceImpl extends ExtendServiceImpl<ProjectTemplateMapper, ProjectTemplate> implements ProjectTemplateService {

    private final ProjectService projectService;

    private final TemplateService templateService;

    private final SysDictItemService sysDictItemService;
    /**
    *  根据QueryObeject查询分页数据
    * @param pageParam 分页参数
    * @param qo 查询参数对象
    * @return PageResult<ProjectTemplatePageVO> 分页数据
    */
    @Override
    public PageResult<ProjectTemplatePageVO> queryPage(PageParam pageParam, ProjectTemplateQO qo) {
        return baseMapper.queryPage(pageParam, qo);
    }

    @Override
    public ProjectTemplateDTO detail(Integer projectTemplateId) {
        ProjectTemplate projectTemplate = getById(projectTemplateId);
        Integer fileType = projectTemplate.getFileType();

        SysDictItem sysDictItem= sysDictItemService.getByDictKeyAndValue(SysDictKey.FILE_TYPE_KEY.getKey(),fileType);
        Integer projectId = projectTemplate.getProjectId();

        Project project = projectService.getById(projectId);
        Integer templateId = projectTemplate.getTemplateId();
        Template template = templateService.getById(templateId);

        ProjectTemplateDTO projectTemplateDTO = new ProjectTemplateDTO();
        projectTemplateDTO.setProjectTemplateId(projectTemplate.getProjectTemplateId());
        projectTemplateDTO.setProjectId(projectId);
        projectTemplateDTO.setProjectName(project.getProjectName());
        projectTemplateDTO.setTemplateId(templateId);
        projectTemplateDTO.setTemplateName(template.getTemplateName());
        projectTemplateDTO.setTemplateDesc(template.getRemark());
        projectTemplateDTO.setFileType(fileType);
        projectTemplateDTO.setFileName(sysDictItem.getName());
        projectTemplateDTO.setSealId(projectTemplate.getSealId());
        projectTemplateDTO.setKeystoreId(projectTemplate.getKeystoreId());
        projectTemplateDTO.setSealWay(projectTemplate.getSealWay());
        projectTemplateDTO.setSealLocation(projectTemplate.getSealLocation());
        projectTemplateDTO.setSealKeyword(projectTemplate.getSealKeyword());
        return projectTemplateDTO;
    }

    @Override
    public boolean saveSignInfo(ProjectTemplateSignReqDTO projectTemplateSignReqDTO) {
        ProjectTemplate projectTemplate = ProjectTemplateConverter.INSTANCE.reqDtoToPo(projectTemplateSignReqDTO);
        return updateById(projectTemplate);
    }

    @Override
    public ProjectTemplate getByPidAndFileType(Integer projectId, Integer  fileType) {
        return getOne(Wrappers.lambdaQuery(ProjectTemplate.class).eq(ProjectTemplate::getProjectId, projectId)
                .eq(ProjectTemplate::getFileType,fileType));
    }

    @Override
    public long countByKeystoreId(Integer keystoreId) {
        return count(Wrappers.lambdaQuery(ProjectTemplate.class).eq(ProjectTemplate::getKeystoreId, keystoreId));
    }
}
