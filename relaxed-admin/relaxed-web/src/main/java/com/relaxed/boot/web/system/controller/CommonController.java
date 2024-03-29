package com.relaxed.boot.web.system.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.web.system.domain.FileDeleteReq;
import com.relaxed.boot.web.system.domain.FileUploadReq;
import com.relaxed.common.model.result.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Yakir
 * @Topic CommonController
 * @Description
 * @date 2022/11/27 12:35
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
@Tag(name = "通用服务")
public class CommonController {

	@PostMapping("/file/upload")
	public R<?> uploadFile(MultipartFile file, FileUploadReq fileUploadReq) {
		String relatedPath = Optional.ofNullable(fileUploadReq.getRelatedPath()).orElse("profile/upload");
		// 上传文件路径
		FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(), relatedPath, file,
				FileConfig.create().splitDate(fileUploadReq.isSplitDate()));
		String fullFilePath = fileMeta.getRelativeFilePath();
		Map<String, String> data = new HashMap<>();
		String fullUrl = RelaxedConfig.getUrl() + fullFilePath;
		data.put("url", fullUrl);
		data.put("path", fullFilePath);
		data.put("fileId", fileMeta.getFileId());
		data.put("oldFilename", fileMeta.getOriginalFilename());
		data.put("filename", fileMeta.getFilename());
		return R.ok(data);

	}

	@PostMapping("/file/delete")
	public R<?> deleteFile(@RequestBody FileDeleteReq fileDeleteReq) {
		// 本地文件路径
		String localFilePath = RelaxedConfig.getProfile()
				+ StrUtil.removePrefixIgnoreCase(fileDeleteReq.getUrl(), RelaxedConfig.getUrl());
		FileUtil.del(localFilePath);
		return R.ok();

	}

}
