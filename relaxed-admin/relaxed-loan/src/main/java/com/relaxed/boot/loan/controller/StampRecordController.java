package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.StampRecord;
import com.relaxed.boot.loan.model.qo.StampRecordQO;
import com.relaxed.boot.loan.model.vo.StampRecordPageVO;
import com.relaxed.boot.loan.service.StampRecordService;
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
 * 签章记录
 *
 * @author yakir 2023-01-29 09:31:45
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/stamp-record")
@Tag(name = "签章记录管理")
public class StampRecordController {

	private final StampRecordService stampRecordService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param stampRecordQO 签章记录查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<StampRecordPageVO>> getStampRecordPage(PageParam pageParam, StampRecordQO stampRecordQO) {
		return R.ok(stampRecordService.queryPage(pageParam, stampRecordQO));
	}

	/**
	 * 新增签章记录
	 * @param stampRecord 签章记录
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增签章记录")
	@CreateLog(msg = "新增签章记录")
	@PostMapping
	public R<Void> save(@RequestBody StampRecord stampRecord) {
		return stampRecordService.save(stampRecord) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增签章记录失败");
	}

	/**
	 * 修改签章记录
	 * @param stampRecord 签章记录
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改签章记录")
	@UpdateLog(msg = "修改签章记录")
	@PutMapping
	public R<Void> updateById(@RequestBody StampRecord stampRecord) {
		return stampRecordService.updateById(stampRecord) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改签章记录失败");
	}

	/**
	 * 通过id删除签章记录
	 * @param stampRecordId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除签章记录")
	@DeleteLog(msg = "通过id删除签章记录")
	@DeleteMapping("/{stampRecordId}")
	public R<Void> removeById(@PathVariable("stampRecordId") Integer stampRecordId) {
		return stampRecordService.removeById(stampRecordId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除签章记录失败");
	}

}
