package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Bill;
import com.relaxed.boot.loan.model.qo.BillQO;
import com.relaxed.boot.loan.model.vo.BillPageVO;
import com.relaxed.boot.loan.service.BillService;
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
 * @author yakir 2023-01-10 10:32:31
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/bill")
@Tag(name = "管理")
public class BillController {

	private final BillService billService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param billQO 查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<BillPageVO>> getBillPage(PageParam pageParam, @Valid BillQO billQO) {
		return R.ok(billService.queryPage(pageParam, billQO));
	}

	/**
	 * 新增
	 * @param bill
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增")
	@CreateLog(msg = "新增")
	@PostMapping
	public R<Void> save(@RequestBody Bill bill) {
		return billService.save(bill) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增失败");
	}

	/**
	 * 修改
	 * @param bill
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改")
	@UpdateLog(msg = "修改")
	@PutMapping
	public R<Void> updateById(@RequestBody Bill bill) {
		return billService.updateById(bill) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改失败");
	}

	/**
	 * 通过id删除
	 * @param billId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除")
	@DeleteLog(msg = "通过id删除")
	@DeleteMapping("/{billId}")
	public R<Void> removeById(@PathVariable("billId") Long billId) {
		return billService.removeById(billId) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除失败");
	}

}
