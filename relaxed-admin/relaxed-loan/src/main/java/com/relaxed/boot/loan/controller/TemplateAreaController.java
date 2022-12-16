package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.TemplateArea;
import com.relaxed.boot.loan.model.qo.TemplateAreaQO;
import com.relaxed.boot.loan.model.vo.TemplateAreaPageVO;
import com.relaxed.boot.loan.service.TemplateAreaService;
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

import javax.validation.Valid;


/**
 * 模板文件文本域表
 *
 * @author Yakir 2022-12-16 11:18:37
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/template-area" )
@Tag(name = "模板文件文本域表管理")
public class TemplateAreaController {

    private final  TemplateAreaService templateAreaService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param templateAreaQO 模板文件文本域表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<TemplateAreaPageVO>> getTemplateAreaPage(
            PageParam pageParam, @Valid TemplateAreaQO templateAreaQO) {
        return R.ok(templateAreaService.queryPage(pageParam, templateAreaQO));
    }

    /**
     * 新增模板文件文本域表
     * @param templateArea 模板文件文本域表
     * @return R 通用返回体
     */
    @Operation(summary = "新增模板文件文本域表")
    @CreateLog(msg = "新增模板文件文本域表" )
    @PostMapping
    public R<Void> save(@RequestBody TemplateArea templateArea) {
        return templateAreaService.save(templateArea) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增模板文件文本域表失败");
    }

    /**
     * 修改模板文件文本域表
     * @param templateArea 模板文件文本域表
     * @return R 通用返回体
     */
    @Operation(summary = "修改模板文件文本域表")
    @UpdateLog(msg = "修改模板文件文本域表" )
    @PutMapping
    public R<Void> updateById(@RequestBody TemplateArea templateArea) {
        return templateAreaService.updateById(templateArea) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改模板文件文本域表失败");
    }

    /**
     * 通过id删除模板文件文本域表
     * @param areaId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除模板文件文本域表")
    @DeleteLog(msg = "通过id删除模板文件文本域表" )
    @DeleteMapping("/{areaId}" )
    public R<Void> removeById(@PathVariable("areaId") Integer areaId) {
        return templateAreaService.removeById(areaId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除模板文件文本域表失败");
    }

}
