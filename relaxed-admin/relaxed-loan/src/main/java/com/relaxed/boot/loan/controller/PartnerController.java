package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Partner;
import com.relaxed.boot.loan.model.qo.PartnerQO;
import com.relaxed.boot.loan.model.vo.PartnerPageVO;
import com.relaxed.boot.loan.service.PartnerService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 合作方
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/partner")
@Tag(name = "合作方管理")
public class PartnerController {

	private final PartnerService partnerService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param partnerQO 合作方查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<PartnerPageVO>> getPartnerPage(PageParam pageParam, PartnerQO partnerQO) {
		return R.ok(partnerService.queryPage(pageParam, partnerQO));
	}

	/**
	 * 合作方下拉列表
	 * @return R 通用返回体
	 */
	@Operation(summary = "合作方下拉列表")
	@GetMapping("/select")
	public R<List<SelectData>> getPartnerList() {
		return R.ok(partnerService.queryPartnerList());
	}

	/**
	 * 新增合作方
	 * @param partner 合作方
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增合作方")
	@CreateLog(msg = "新增合作方")
	@PostMapping
	public R<Void> save(@RequestBody Partner partner) {
		return partnerService.save(partner) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增合作方失败");
	}

	/**
	 * 修改合作方
	 * @param partner 合作方
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改合作方")
	@UpdateLog(msg = "修改合作方")
	@PutMapping
	public R<Void> updateById(@RequestBody Partner partner) {
		return partnerService.updateById(partner) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改合作方失败");
	}

	/**
	 * 通过id删除合作方
	 * @param partnerId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除合作方")
	@DeleteLog(msg = "通过id删除合作方")
	@DeleteMapping("/{partnerId}")
	public R<Void> removeById(@PathVariable("partnerId") Integer partnerId) {
		return partnerService.removeById(partnerId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除合作方失败");
	}

}
