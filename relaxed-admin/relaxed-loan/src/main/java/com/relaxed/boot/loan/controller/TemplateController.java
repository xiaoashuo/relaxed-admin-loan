package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.qo.TemplateQO;
import com.relaxed.boot.loan.model.vo.TemplatePageVO;
import com.relaxed.boot.loan.service.TemplateService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 模板文件配置表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/template" )
@Tag(name = "模板文件配置表管理")
public class TemplateController {

    private final  TemplateService templateService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param templateQO 模板文件配置表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<TemplatePageVO>> getTemplatePage(
            PageParam pageParam, TemplateQO templateQO) {
        return R.ok(templateService.queryPage(pageParam, templateQO));
    }

    /**
     * 新增模板文件配置表
     * @param template 模板文件配置表
     * @return R 通用返回体
     */
    @Operation(summary = "新增模板文件配置表")
    @CreateLog(msg = "新增模板文件配置表" )
    @PostMapping
    public R<Void> save(@RequestBody Template template) {
        return templateService.save(template) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增模板文件配置表失败");
    }

    /**
     * 修改模板文件配置表
     * @param template 模板文件配置表
     * @return R 通用返回体
     */
    @Operation(summary = "修改模板文件配置表")
    @UpdateLog(msg = "修改模板文件配置表" )
    @PutMapping
    public R<Void> updateById(@RequestBody Template template) {
        return templateService.updateById(template) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改模板文件配置表失败");
    }

    /**
     * 通过id删除模板文件配置表
     * @param templateId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除模板文件配置表")
    @DeleteLog(msg = "通过id删除模板文件配置表" )
    @DeleteMapping("/{templateId}" )
    public R<Void> removeById(@PathVariable("templateId") Integer templateId) {
        return templateService.removeById(templateId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除模板文件配置表失败");
    }

}