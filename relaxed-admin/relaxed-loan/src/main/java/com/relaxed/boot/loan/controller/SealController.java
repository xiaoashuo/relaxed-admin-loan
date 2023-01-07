package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.config.download.StreamDownloadHandler;
import com.relaxed.boot.loan.manage.SealManage;
import com.relaxed.boot.loan.model.dto.PreviewInfoDTO;
import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.qo.SealQO;
import com.relaxed.boot.loan.model.vo.SealPageVO;
import com.relaxed.boot.loan.service.SealService;
import com.relaxed.common.log.operation.annotation.CreateLog;
import com.relaxed.common.log.operation.annotation.DeleteLog;
import com.relaxed.common.log.operation.annotation.UpdateLog;
import com.relaxed.common.model.domain.PageParam;
import com.relaxed.common.model.domain.PageResult;
import com.relaxed.common.model.domain.SelectData;
import com.relaxed.common.model.result.BaseResultCode;
import com.relaxed.common.model.result.R;
import com.relaxed.starter.download.annotation.ResponseDownload;
import com.relaxed.starter.download.domain.DownloadModel;
import com.relaxed.starter.download.enums.DownTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 签章图片表
 *
 * @author yakir 2022-12-15 10:25:25
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/seal")
@Tag(name = "签章图片表管理")
public class SealController {

	private final SealService sealService;

	private final SealManage sealManage;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param sealQO 签章图片表查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<SealPageVO>> getSealPage(PageParam pageParam, SealQO sealQO) {
		return R.ok(sealService.queryPage(pageParam, sealQO));
	}

	/**
	 * 签章图片列表
	 * @return R 通用返回体
	 */
	@Operation(summary = "签章图片列表")
	@GetMapping("/list")
	public R<List<SealPageVO>> getSealList() {
		return R.ok(sealService.queryList());
	}

	/**
	 * 预览PDF
	 * @return R 通用返回体
	 */
	@Operation(summary = "预览PDF")
	@PostMapping("/preview")
	@ResponseDownload
	public DownloadModel preview(@RequestBody PreviewInfoDTO previewInfoDTO) {
		return sealManage.preview(previewInfoDTO);
	}
	/**
	 * 预览PDF
	 * @return R 通用返回体
	 */
	@Operation(summary = "预览PDF")
	@PostMapping("/preview/template")
	@ResponseDownload(channel = DownTypeEnum.OTHER,customHandler = StreamDownloadHandler.class)
	public DownloadModel previewTemplate(@RequestParam Integer templateId) {
		return sealManage.previewTemplate(templateId);
	}

	/**
	 * 签章图片下拉列表
	 * @return R 通用返回体
	 */
	@Operation(summary = "签章图片下拉列表")
	@GetMapping("/select")
	public R<List<SelectData>> getSealSelectedList() {
		return R.ok(sealService.querySealList());
	}

	/**
	 * 新增签章图片表
	 * @param seal 签章图片表
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增签章图片表")
	@CreateLog(msg = "新增签章图片表")
	@PostMapping
	public R<Void> save(@RequestBody Seal seal) {
		return sealService.saveSeal(seal) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增签章图片表失败");
	}

	/**
	 * 修改签章图片表
	 * @param seal 签章图片表
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改签章图片表")
	@UpdateLog(msg = "修改签章图片表")
	@PutMapping
	public R<Void> updateById(@RequestBody Seal seal) {
		return sealService.updateById(seal) ? R.ok() : R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改签章图片表失败");
	}

	/**
	 * 通过id删除签章图片表
	 * @param sealId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除签章图片表")
	@DeleteLog(msg = "通过id删除签章图片表")
	@DeleteMapping("/{sealId}")
	public R<Void> removeById(@PathVariable("sealId") Integer sealId) {
		return sealService.removeById(sealId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除签章图片表失败");
	}

}
