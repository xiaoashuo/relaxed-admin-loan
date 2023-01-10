package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Audit;
import com.relaxed.boot.loan.model.qo.AuditQO;
import com.relaxed.boot.loan.model.vo.AuditPageVO;
import com.relaxed.boot.loan.service.AuditService;
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
 * 审核表
 *
 * @author yakir 2023-01-10 10:32:31
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/audit" )
@Tag(name = "审核表管理")
public class AuditController {

    private final  AuditService auditService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param auditQO 审核表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<AuditPageVO>> getAuditPage(
            PageParam pageParam, AuditQO auditQO) {
        return R.ok(auditService.queryPage(pageParam, auditQO));
    }

    /**
     * 新增审核表
     * @param audit 审核表
     * @return R 通用返回体
     */
    @Operation(summary = "新增审核表")
    @CreateLog(msg = "新增审核表" )
    @PostMapping
    public R<Void> save(@RequestBody Audit audit) {
        return auditService.save(audit) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增审核表失败");
    }

    /**
     * 修改审核表
     * @param audit 审核表
     * @return R 通用返回体
     */
    @Operation(summary = "修改审核表")
    @UpdateLog(msg = "修改审核表" )
    @PutMapping
    public R<Void> updateById(@RequestBody Audit audit) {
        return auditService.updateById(audit) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改审核表失败");
    }

    /**
     * 通过id删除审核表
     * @param auditId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除审核表")
    @DeleteLog(msg = "通过id删除审核表" )
    @DeleteMapping("/{auditId}" )
    public R<Void> removeById(@PathVariable("auditId") Long auditId) {
        return auditService.removeById(auditId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除审核表失败");
    }

}
