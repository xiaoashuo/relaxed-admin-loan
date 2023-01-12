package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Loan;
import com.relaxed.boot.loan.model.qo.LoanQO;
import com.relaxed.boot.loan.model.vo.LoanDetailVO;
import com.relaxed.boot.loan.model.vo.LoanPageVO;
import com.relaxed.boot.loan.model.vo.OrderDetailVO;
import com.relaxed.boot.loan.service.LoanService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 借款合同表
 *
 * @author yakir 2023-01-10 10:32:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan" )
@Tag(name = "借款合同表管理")
public class LoanController {

    private final  LoanService loanService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param loanQO 借款合同表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<LoanPageVO>> getLoanPage(
            PageParam pageParam, LoanQO loanQO) {
        return R.ok(loanService.queryPage(pageParam, loanQO));
    }
    @Operation(summary = "借据详情")
    @GetMapping("/detail" )
    public R<LoanDetailVO> getOrderDetail(@RequestParam Long loanId) {
        return R.ok(loanService.getLoanDetail(loanId));
    }
    /**
     * 新增借款合同表
     * @param loan 借款合同表
     * @return R 通用返回体
     */
    @Operation(summary = "新增借款合同表")
    @CreateLog(msg = "新增借款合同表" )
    @PostMapping
    public R<Void> save(@RequestBody Loan loan) {
        return loanService.save(loan) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增借款合同表失败");
    }

    /**
     * 修改借款合同表
     * @param loan 借款合同表
     * @return R 通用返回体
     */
    @Operation(summary = "修改借款合同表")
    @UpdateLog(msg = "修改借款合同表" )
    @PutMapping
    public R<Void> updateById(@RequestBody Loan loan) {
        return loanService.updateById(loan) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改借款合同表失败");
    }

    /**
     * 通过id删除借款合同表
     * @param loanId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除借款合同表")
    @DeleteLog(msg = "通过id删除借款合同表" )
    @DeleteMapping("/{loanId}" )
    public R<Void> removeById(@PathVariable("loanId") Long loanId) {
        return loanService.removeById(loanId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除借款合同表失败");
    }

}
