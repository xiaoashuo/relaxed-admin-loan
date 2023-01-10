package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.OrderGuarantor;
import com.relaxed.boot.loan.model.qo.OrderGuarantorQO;
import com.relaxed.boot.loan.model.vo.OrderGuarantorPageVO;
import com.relaxed.boot.loan.service.OrderGuarantorService;
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
 * 担保机构
 *
 * @author yakir 2023-01-10 10:32:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/order-guarantor" )
@Tag(name = "担保机构管理")
public class OrderGuarantorController {

    private final  OrderGuarantorService orderGuarantorService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param orderGuarantorQO 担保机构查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<OrderGuarantorPageVO>> getOrderGuarantorPage(
            PageParam pageParam, OrderGuarantorQO orderGuarantorQO) {
        return R.ok(orderGuarantorService.queryPage(pageParam, orderGuarantorQO));
    }

    /**
     * 新增担保机构
     * @param orderGuarantor 担保机构
     * @return R 通用返回体
     */
    @Operation(summary = "新增担保机构")
    @CreateLog(msg = "新增担保机构" )
    @PostMapping
    public R<Void> save(@RequestBody OrderGuarantor orderGuarantor) {
        return orderGuarantorService.save(orderGuarantor) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增担保机构失败");
    }

    /**
     * 修改担保机构
     * @param orderGuarantor 担保机构
     * @return R 通用返回体
     */
    @Operation(summary = "修改担保机构")
    @UpdateLog(msg = "修改担保机构" )
    @PutMapping
    public R<Void> updateById(@RequestBody OrderGuarantor orderGuarantor) {
        return orderGuarantorService.updateById(orderGuarantor) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改担保机构失败");
    }

    /**
     * 通过id删除担保机构
     * @param guarantorId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除担保机构")
    @DeleteLog(msg = "通过id删除担保机构" )
    @DeleteMapping("/{guarantorId}" )
    public R<Void> removeById(@PathVariable("guarantorId") Long guarantorId) {
        return orderGuarantorService.removeById(guarantorId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除担保机构失败");
    }

}
