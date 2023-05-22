package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.manage.OrderManage;
import com.relaxed.boot.loan.model.dto.OrderDTO;
import com.relaxed.boot.loan.model.dto.SaveOrderResult;
import com.relaxed.boot.loan.model.entity.Order;
import com.relaxed.boot.loan.model.qo.OrderBorrowQO;
import com.relaxed.boot.loan.model.qo.OrderQO;
import com.relaxed.boot.loan.model.vo.OrderBorrowPageVO;
import com.relaxed.boot.loan.model.vo.OrderDetailVO;
import com.relaxed.boot.loan.model.vo.OrderPageVO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yakir 2023-01-10 10:32:31
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/order")
@Tag(name = "订单管理")
public class OrderController {

	private final OrderService orderService;

	private final OrderManage orderManage;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param orderQO 查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<OrderPageVO>> getOrderPage(PageParam pageParam, OrderQO orderQO) {
		return R.ok(orderService.queryPage(pageParam, orderQO));
	}

	@Operation(summary = "订单详情")
	@GetMapping("/form/detail")
	public R<OrderDTO> getOrderFormDetailDTO(@RequestParam Long orderId) {
		return R.ok(orderService.getOrderFormDetailDTO(orderId));
	}

	@Operation(summary = "订单详情")
	@GetMapping("/detail")
	public R<OrderDetailVO> getOrderDetail(@RequestParam Long orderId) {
		return R.ok(orderService.getOrderDetail(orderId));
	}

	/**
	 * 新增
	 * @param order
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增")
	@CreateLog(msg = "新增")
	@PostMapping
	public R<SaveOrderResult> saveOrder(@RequestBody OrderDTO orderDTO) {
		return R.ok(orderManage.saveOrder(orderDTO));
	}

	@Operation(summary = "提交进件")
	@CreateLog(msg = "提交进件")
	@PostMapping("/submit")
	public R<SaveOrderResult> submitOrder(@RequestParam Long orderId) {
		return R.ok(orderManage.submitOrder(orderId));
	}

	/**
	 * 修改
	 * @param order
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改")
	@UpdateLog(msg = "修改")
	@PutMapping
	public R<Void> updateById(@RequestBody Order order) {
		return orderService.updateById(order) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改失败");
	}

	/**
	 * 通过id删除
	 * @param orderId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除")
	@DeleteLog(msg = "通过id删除")
	@DeleteMapping("/{orderId}")
	public R<Void> removeById(@PathVariable("orderId") Long orderId) {
		return orderService.removeById(orderId) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除失败");
	}

}
