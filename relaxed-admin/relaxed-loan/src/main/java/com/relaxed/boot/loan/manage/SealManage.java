package com.relaxed.boot.loan.manage;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.config.download.StreamDownloadModel;
import com.relaxed.boot.loan.model.dto.PreviewInfoDTO;
import com.relaxed.boot.loan.model.entity.Seal;
import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.service.SealService;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.boot.loan.util.FileConvert;
import com.relaxed.boot.loan.util.KeywordLocation;
import com.relaxed.boot.loan.util.PdfUtil;
import com.relaxed.boot.loan.util.PreviewSignInfo;
import com.relaxed.boot.loan.util.pdf.ImageSignMeta;
import com.relaxed.boot.loan.util.pdf.PdfTemplate;
import com.relaxed.common.core.exception.BusinessException;
import com.relaxed.common.model.result.SysResultCode;
import com.relaxed.starter.download.domain.DownloadModel;
import com.relaxed.starter.download.functions.DownloadCallback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Yakir
 * @Topic SealManage
 * @Description
 * @date 2022/12/18 16:00
 * @Version 1.0
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class SealManage {

	private final SealService sealService;

	private final TemplateService templateService;

	public DownloadModel preview(PreviewInfoDTO previewInfoDTO) {
		Integer templateId = previewInfoDTO.getTemplateId();
		Template template = templateService.getById(templateId);
		Assert.notNull(template, () -> {
			throw new BusinessException(SysResultCode.SERVER_ERROR.getCode(), "模板不存在");
		});
		// 模板路径
		String templatePath = RelaxedConfig.getProfile() + template.getTemplateUrl();
		File templateWordFile = new File(templatePath);

		try {
			// pdf 模板
			ByteArrayOutputStream templatePdfFileByte = new ByteArrayOutputStream();
			FileConvert.doc2pdf(new FileInputStream(templateWordFile), templatePdfFileByte);
			Integer sealId = previewInfoDTO.getSealId();
			Seal seal = sealService.getById(sealId);

			// 签章路径
			String sealPath = RelaxedConfig.getProfile() + seal.getSealAddress();
			String keyword = previewInfoDTO.getKeyword();

			String fileName = IdUtil.simpleUUID() + ".pdf";
			String profile = RelaxedConfig.getProfile();
			File dirFile = new File(profile, "preview");
			FileUtil.mkdir(dirFile);
			File targetFile = new File(dirFile, fileName);

			byte[] pdfContent = templatePdfFileByte.toByteArray();
			ByteArrayInputStream pdfByteInputStream = new ByteArrayInputStream(pdfContent);

			List<KeywordLocation> keywordLocations = PdfTemplate.extractKeywordLocation(pdfByteInputStream, keyword);
			pdfByteInputStream.reset();
			ImageSignMeta signMeta = new ImageSignMeta();
			signMeta.setImgPath(sealPath);
			signMeta.setContentBefore(false);
			signMeta.setOffsetX(0);
			signMeta.setOffsetY(50);
			signMeta.setKeywordLocationList(keywordLocations);
			PdfTemplate.addImage(pdfByteInputStream, new FileOutputStream(targetFile), signMeta);
			DownloadModel downloadModel = new DownloadModel(dirFile.getAbsolutePath(), fileName, "pdf", StrPool.SLASH);
			DownloadCallback downloadCallback = () -> {
				// 清理工作
				FileUtil.del(targetFile);
			};
			downloadModel.setDownloadCallback(downloadCallback);
			return downloadModel;
		}
		catch (Exception e) {
			throw new BusinessException(SysResultCode.SERVER_ERROR.getCode(), "预览文件异常", e);
		}
		finally {

		}

	}

	public DownloadModel previewTemplate(Integer templateId) {

		Template template = templateService.getById(templateId);
		Assert.notNull(template, () -> {
			throw new BusinessException(SysResultCode.SERVER_ERROR.getCode(), "模板不存在");
		});
		// 模板路径
		String templatePath = RelaxedConfig.getProfile() + template.getTemplateUrl();
		File templateWordFile = new File(templatePath);

		try {
			// pdf 模板
			ByteArrayOutputStream templatePdfFileByte = new ByteArrayOutputStream();
			FileConvert.doc2pdf(new FileInputStream(templateWordFile), templatePdfFileByte);
			StreamDownloadModel streamDownloadModel = new StreamDownloadModel();
			streamDownloadModel.setContent(templatePdfFileByte.toByteArray());
			streamDownloadModel.setFileName(template.getTemplateFilename());
			streamDownloadModel.setFileType("pdf");
			return streamDownloadModel;
		}
		catch (Exception e) {
			throw new BusinessException(SysResultCode.SERVER_ERROR.getCode(), "预览文件异常", e);
		}

	}

}
