package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Product;
import com.relaxed.boot.loan.model.qo.ProductQO;
import com.relaxed.boot.loan.model.vo.ProductPageVO;
import com.relaxed.boot.loan.service.ProductService;
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
 * 信托产品表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/product" )
@Tag(name = "信托产品表管理")
public class ProductController {

    private final  ProductService productService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param productQO 信托产品表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<ProductPageVO>> getProductPage(
            PageParam pageParam, ProductQO productQO) {
        return R.ok(productService.queryPage(pageParam, productQO));
    }

    /**
     * 新增信托产品表
     * @param product 信托产品表
     * @return R 通用返回体
     */
    @Operation(summary = "新增信托产品表")
    @CreateLog(msg = "新增信托产品表" )
    @PostMapping
    public R<Void> save(@RequestBody Product product) {
        return productService.save(product) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增信托产品表失败");
    }

    /**
     * 修改信托产品表
     * @param product 信托产品表
     * @return R 通用返回体
     */
    @Operation(summary = "修改信托产品表")
    @UpdateLog(msg = "修改信托产品表" )
    @PutMapping
    public R<Void> updateById(@RequestBody Product product) {
        return productService.updateById(product) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改信托产品表失败");
    }

    /**
     * 通过id删除信托产品表
     * @param productId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除信托产品表")
    @DeleteLog(msg = "通过id删除信托产品表" )
    @DeleteMapping("/{productId}" )
    public R<Void> removeById(@PathVariable("productId") Integer productId) {
        return productService.removeById(productId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除信托产品表失败");
    }

}
