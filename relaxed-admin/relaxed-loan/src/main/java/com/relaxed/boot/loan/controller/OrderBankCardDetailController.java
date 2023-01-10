package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.OrderBankCardDetail;
import com.relaxed.boot.loan.model.qo.OrderBankCardDetailQO;
import com.relaxed.boot.loan.model.vo.OrderBankCardDetailPageVO;
import com.relaxed.boot.loan.service.OrderBankCardDetailService;
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
 * 订单银行卡放款详情表
 *
 * @author yakir 2023-01-10 10:32:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/order-bank-card-detail" )
@Tag(name = "订单银行卡放款详情表管理")
public class OrderBankCardDetailController {

    private final  OrderBankCardDetailService orderBankCardDetailService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param orderBankCardDetailQO 订单银行卡放款详情表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<OrderBankCardDetailPageVO>> getOrderBankCardDetailPage(
            PageParam pageParam, OrderBankCardDetailQO orderBankCardDetailQO) {
        return R.ok(orderBankCardDetailService.queryPage(pageParam, orderBankCardDetailQO));
    }

    /**
     * 新增订单银行卡放款详情表
     * @param orderBankCardDetail 订单银行卡放款详情表
     * @return R 通用返回体
     */
    @Operation(summary = "新增订单银行卡放款详情表")
    @CreateLog(msg = "新增订单银行卡放款详情表" )
    @PostMapping
    public R<Void> save(@RequestBody OrderBankCardDetail orderBankCardDetail) {
        return orderBankCardDetailService.save(orderBankCardDetail) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增订单银行卡放款详情表失败");
    }

    /**
     * 修改订单银行卡放款详情表
     * @param orderBankCardDetail 订单银行卡放款详情表
     * @return R 通用返回体
     */
    @Operation(summary = "修改订单银行卡放款详情表")
    @UpdateLog(msg = "修改订单银行卡放款详情表" )
    @PutMapping
    public R<Void> updateById(@RequestBody OrderBankCardDetail orderBankCardDetail) {
        return orderBankCardDetailService.updateById(orderBankCardDetail) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改订单银行卡放款详情表失败");
    }

    /**
     * 通过id删除订单银行卡放款详情表
     * @param detailId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除订单银行卡放款详情表")
    @DeleteLog(msg = "通过id删除订单银行卡放款详情表" )
    @DeleteMapping("/{detailId}" )
    public R<Void> removeById(@PathVariable("detailId") Long detailId) {
        return orderBankCardDetailService.removeById(detailId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除订单银行卡放款详情表失败");
    }

}
