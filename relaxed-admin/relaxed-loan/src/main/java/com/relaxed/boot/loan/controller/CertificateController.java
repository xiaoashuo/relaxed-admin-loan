package com.relaxed.boot.loan.controller;

import com.relaxed.boot.loan.model.entity.Certificate;
import com.relaxed.boot.loan.model.qo.CertificateQO;
import com.relaxed.boot.loan.model.vo.CertificatePageVO;
import com.relaxed.boot.loan.service.CertificateService;
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
 * 签章证书表
 *
 * @author yakir 2022-12-14 11:36:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan/certificate")
@Tag(name = "签章证书表管理")
public class CertificateController {

	private final CertificateService certificateService;

	/**
	 * 分页查询
	 * @param pageParam 分页参数
	 * @param certificateQO 签章证书表查询对象
	 * @return R 通用返回体
	 */
	@Operation(summary = "分页查询")
	@GetMapping("/page")
	public R<PageResult<CertificatePageVO>> getCertificatePage(PageParam pageParam, CertificateQO certificateQO) {
		return R.ok(certificateService.queryPage(pageParam, certificateQO));
	}

	/**
	 * 签章证书下拉列表
	 * @return R 通用返回体
	 */
	@Operation(summary = "签章证书下拉列表")
	@GetMapping("/select")
	public R<List<SelectData>> getCertificateList() {
		return R.ok(certificateService.queryCertificateList());
	}

	/**
	 * 新增签章证书表
	 * @param certificate 签章证书表
	 * @return R 通用返回体
	 */
	@Operation(summary = "新增签章证书表")
	@CreateLog(msg = "新增签章证书表")
	@PostMapping
	public R<Void> save(@RequestBody Certificate certificate) {
		return certificateService.saveCertificate(certificate) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "新增签章证书表失败");
	}

	/**
	 * 修改签章证书表
	 * @param certificate 签章证书表
	 * @return R 通用返回体
	 */
	@Operation(summary = "修改签章证书表")
	@UpdateLog(msg = "修改签章证书表")
	@PutMapping
	public R<Void> updateById(@RequestBody Certificate certificate) {
		return certificateService.updateById(certificate) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "修改签章证书表失败");
	}

	/**
	 * 通过id删除签章证书表
	 * @param certificateId id
	 * @return R 通用返回体
	 */
	@Operation(summary = "通过id删除签章证书表")
	@DeleteLog(msg = "通过id删除签章证书表")
	@DeleteMapping("/{certificateId}")
	public R<Void> removeById(@PathVariable("certificateId") Integer certificateId) {
		return certificateService.removeById(certificateId) ? R.ok()
				: R.failed(BaseResultCode.UPDATE_DATABASE_ERROR, "通过id删除签章证书表失败");
	}

}
