package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.BankCode;
import com.relaxed.boot.loan.model.qo.BankCodeQO;
import com.relaxed.boot.loan.model.vo.BankCodePageVO;
import com.relaxed.boot.loan.service.BankCodeService;
import com.relaxed.common.log.operation.annotation.CreateLog;
import com.relaxed.common.log.operation.annotation.DeleteLog;
import com.relaxed.common.log.operation.annotation.UpdateLog;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
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

import java.util.List;


/**
 * 银行编码表
 *
 * @author yakir 2023-01-10 10:13:02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/bank-code" )
@Tag(name = "银行编码表管理")
public class BankCodeController {

    private final  BankCodeService bankCodeService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param bankCodeQO 银行编码表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<BankCodePageVO>> getBankCodePage(
            PageParam pageParam, BankCodeQO bankCodeQO) {
        return R.ok(bankCodeService.queryPage(pageParam, bankCodeQO));
    }
    /**
     * 银行编码下拉列表
     * @return R 通用返回体
     */
    @Operation(summary = "银行编码下拉列表")
    @GetMapping("/select")
    public R<List<SelectData>> getBankCodeSelectedList() {
        return R.ok(bankCodeService.querySelectData());
    }
    /**
     * 新增银行编码表
     * @param bankCode 银行编码表
     * @return R 通用返回体
     */
    @Operation(summary = "新增银行编码表")
    @CreateLog(msg = "新增银行编码表" )
    @PostMapping
    public R<Void> save(@RequestBody BankCode bankCode) {
        return bankCodeService.save(bankCode) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增银行编码表失败");
    }

    /**
     * 修改银行编码表
     * @param bankCode 银行编码表
     * @return R 通用返回体
     */
    @Operation(summary = "修改银行编码表")
    @UpdateLog(msg = "修改银行编码表" )
    @PutMapping
    public R<Void> updateById(@RequestBody BankCode bankCode) {
        return bankCodeService.updateById(bankCode) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改银行编码表失败");
    }

    /**
     * 通过id删除银行编码表
     * @param bankCodeId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除银行编码表")
    @DeleteLog(msg = "通过id删除银行编码表" )
    @DeleteMapping("/{bankCodeId}" )
    public R<Void> removeById(@PathVariable("bankCodeId") Integer bankCodeId) {
        return bankCodeService.removeById(bankCodeId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除银行编码表失败");
    }

}
