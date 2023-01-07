package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.dto.ProjectTemplateDTO;
import com.relaxed.boot.loan.model.dto.ProjectTemplateSignReqDTO;
import com.relaxed.boot.loan.model.entity.ProjectTemplate;
import com.relaxed.boot.loan.model.qo.ProjectTemplateQO;
import com.relaxed.boot.loan.model.vo.ProjectTemplatePageVO;
import com.relaxed.boot.loan.service.ProjectTemplateService;
import com.relaxed.common.log.operation.annotation.CreateLog;
import com.relaxed.common.log.operation.annotation.DeleteLog;
import com.relaxed.common.log.operation.annotation.UpdateLog;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.common.model.result.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 项目模板配置表
 *
 * @author yakir 2023-01-02 09:55:28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/project-template" )
@Tag(name = "项目模板配置表管理")
public class ProjectTemplateController {

    private final  ProjectTemplateService projectTemplateService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param projectTemplateQO 项目模板配置表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<ProjectTemplatePageVO>> getProjectTemplatePage(
            PageParam pageParam, @Valid ProjectTemplateQO projectTemplateQO) {
        return R.ok(projectTemplateService.queryPage(pageParam, projectTemplateQO));
    }
    @Operation(summary = "分页查询")
    @GetMapping("/detail" )
    public R<ProjectTemplateDTO> getProjectTemplateDetail(@RequestParam Integer projectTemplateId) {
        return R.ok(projectTemplateService.detail(projectTemplateId));
    }

    /**
     * 新增项目模板配置表
     * @param projectTemplate 项目模板配置表
     * @return R 通用返回体
     */
    @Operation(summary = "新增项目模板配置表")
    @CreateLog(msg = "新增项目模板配置表" )
    @PostMapping
    public R<Void> save(@RequestBody ProjectTemplate projectTemplate) {
        return projectTemplateService.save(projectTemplate) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增项目模板配置表失败");
    }
    /**
     * 项目签章配置
     * @param projectTemplateSignReqDTO 项目签章配置
     * @return R 通用返回体
     */
    @Operation(summary = "项目签章配置")
    @CreateLog(msg = "项目签章配置" )
    @PostMapping("/signConfig")
    public R<Void> saveSignInfo(@RequestBody ProjectTemplateSignReqDTO projectTemplateSignReqDTO) {
        return projectTemplateService.saveSignInfo(projectTemplateSignReqDTO) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增项目签章配置失败");
    }
    /**
     * 修改项目模板配置表
     * @param projectTemplate 项目模板配置表
     * @return R 通用返回体
     */
    @Operation(summary = "修改项目模板配置表")
    @UpdateLog(msg = "修改项目模板配置表" )
    @PutMapping
    public R<Void> updateById(@RequestBody ProjectTemplate projectTemplate) {
        return projectTemplateService.updateById(projectTemplate) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改项目模板配置表失败");
    }

    /**
     * 通过id删除项目模板配置表
     * @param projectTemplateId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除项目模板配置表")
    @DeleteLog(msg = "通过id删除项目模板配置表" )
    @DeleteMapping("/{projectTemplateId}" )
    public R<Void> removeById(@PathVariable("projectTemplateId") Integer projectTemplateId) {
        return projectTemplateService.removeById(projectTemplateId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除项目模板配置表失败");
    }

}