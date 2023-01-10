package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.BillItemFiller;
import com.relaxed.boot.loan.model.qo.BillItemFillerQO;
import com.relaxed.boot.loan.model.vo.BillItemFillerPageVO;
import com.relaxed.boot.loan.service.BillItemFillerService;
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
 * 
 *
 * @author yakir 2023-01-10 10:32:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/bill-item-filler" )
@Tag(name = "管理")
public class BillItemFillerController {

    private final  BillItemFillerService billItemFillerService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param billItemFillerQO 查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<BillItemFillerPageVO>> getBillItemFillerPage(
            PageParam pageParam, BillItemFillerQO billItemFillerQO) {
        return R.ok(billItemFillerService.queryPage(pageParam, billItemFillerQO));
    }

    /**
     * 新增
     * @param billItemFiller 
     * @return R 通用返回体
     */
    @Operation(summary = "新增")
    @CreateLog(msg = "新增" )
    @PostMapping
    public R<Void> save(@RequestBody BillItemFiller billItemFiller) {
        return billItemFillerService.save(billItemFiller) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增失败");
    }

    /**
     * 修改
     * @param billItemFiller 
     * @return R 通用返回体
     */
    @Operation(summary = "修改")
    @UpdateLog(msg = "修改" )
    @PutMapping
    public R<Void> updateById(@RequestBody BillItemFiller billItemFiller) {
        return billItemFillerService.updateById(billItemFiller) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改失败");
    }

    /**
     * 通过id删除
     * @param itemFillerId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除")
    @DeleteLog(msg = "通过id删除" )
    @DeleteMapping("/{itemFillerId}" )
    public R<Void> removeById(@PathVariable("itemFillerId") Long itemFillerId) {
        return billItemFillerService.removeById(itemFillerId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除失败");
    }

}
