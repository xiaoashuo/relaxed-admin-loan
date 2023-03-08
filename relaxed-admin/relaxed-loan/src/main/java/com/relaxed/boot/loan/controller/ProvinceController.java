package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Province;
import com.relaxed.boot.loan.model.qo.ProvinceQO;
import com.relaxed.boot.loan.model.vo.ProvincePageVO;
import com.relaxed.boot.loan.service.ProvinceService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 省市区码表
 *
 * @author Yakir 2023-03-08 10:44:09
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/province" )
@Tag(name = "省市区码表管理")
public class ProvinceController {

    private final  ProvinceService provinceService;

    /**
     * 分页查询
     * @param pageParam 分页参数
     * @param provinceQO 省市区码表查询对象
     * @return R 通用返回体
     */
    @Operation(summary = "分页查询")
    @GetMapping("/page" )
    public R<PageResult<ProvincePageVO>> getProvincePage(
            PageParam pageParam, ProvinceQO provinceQO) {
        return R.ok(provinceService.queryPage(pageParam, provinceQO));
    }

    @Operation(summary = "分页查询")
    @GetMapping("/parentCode")
    public R<List<SelectData>> listByParentCode(@RequestParam Integer parentCode) {
        return R.ok(provinceService.listByParentCode(parentCode));
    }

    /**
     * 新增省市区码表
     * @param province 省市区码表
     * @return R 通用返回体
     */
    @Operation(summary = "新增省市区码表")
    @CreateLog(msg = "新增省市区码表" )
    @PostMapping
    public R<Void> save(@RequestBody Province province) {
        return provinceService.save(province) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增省市区码表失败");
    }

    /**
     * 修改省市区码表
     * @param province 省市区码表
     * @return R 通用返回体
     */
    @Operation(summary = "修改省市区码表")
    @UpdateLog(msg = "修改省市区码表" )
    @PutMapping
    public R<Void> updateById(@RequestBody Province province) {
        return provinceService.updateById(province) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改省市区码表失败");
    }

    /**
     * 通过id删除省市区码表
     * @param provinceId id
     * @return R 通用返回体
     */
    @Operation(summary = "通过id删除省市区码表")
    @DeleteLog(msg = "通过id删除省市区码表" )
    @DeleteMapping("/{provinceId}" )
    public R<Void> removeById(@PathVariable("provinceId") Integer provinceId) {
        return provinceService.removeById(provinceId) ?
                R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除省市区码表失败");
    }

}
