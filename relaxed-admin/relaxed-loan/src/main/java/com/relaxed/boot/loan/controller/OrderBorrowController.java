package com.relaxed.boot.loan.controller;

import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.entity.OrderBorrow;
import com.relaxed.boot.loan.model.qo.OrderBorrowQO;
import com.relaxed.boot.loan.model.vo.OrderBorrowPageVO;
import com.relaxed.boot.loan.service.OrderBorrowService;
import com.relaxed.boot.loan.service.OrderService;
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
 * 共同借款人
 *
 * @author yakir 2023-01-10 10:32:19
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/order-borrow")
@Tag(name = "共同借款人管理")
public class OrderBorrowController {

	private final OrderBorrowService orderBorrowService;

	private final OrderService orderService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param orderBorrowQO 共同借款人查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<OrderBorrowPageVO>> getOrderBorrowPage(PageParam pageParam, OrderBorrowQO orderBorrowQO) {
		return R.ok(orderBorrowService.queryPage(pageParam, orderBorrowQO));
	}

	/**
	 * 新增共同借款人
	 * @param orderBorrow 共同借款人
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增共同借款人")
	@CreateLog(msg = "新增共同借款人")
	@PostMapping
	public R<Void> save(@RequestBody OrderBorrow orderBorrow) {
		return orderBorrowService.save(orderBorrow) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增共同借款人失败");
	}

	/**
	 * 修改共同借款人
	 * @param orderBorrow 共同借款人
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改共同借款人")
	@UpdateLog(msg = "修改共同借款人")
	@PutMapping
	public R<Void> updateById(@RequestBody OrderBorrow orderBorrow) {
		return orderBorrowService.updateById(orderBorrow) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改共同借款人失败");
	}

	/**
	 * 通过id删除共同借款人
	 * @param userId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除共同借款人")
	@DeleteLog(msg = "通过id删除共同借款人")
	@DeleteMapping("/{userId}")
	public R<Void> removeById(@PathVariable("userId") Long userId) {
		return orderBorrowService.removeById(userId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除共同借款人失败");
	}

}
