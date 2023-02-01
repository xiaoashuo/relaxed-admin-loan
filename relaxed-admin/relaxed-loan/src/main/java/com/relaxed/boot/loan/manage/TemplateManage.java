package com.relaxed.boot.loan.manage;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.framework.config.RelaxedConfig;
import com.relaxed.boot.loan.constants.LoanUploadPath;
import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.model.entity.TemplateArea;
import com.relaxed.boot.loan.service.TemplateAreaService;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.boot.loan.util.word.StoreWordTemplate;
import com.relaxed.boot.loan.util.word.domain.ElementMeta;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yakir
 * @Topic TemplateManage
 * @Description
 * @date 2022/12/16 13:58
 * @Version 1.0
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class TemplateManage {

	private final TransactionTemplate transactionTemplate;

	private final TemplateService templateService;

	private final TemplateAreaService templateAreaService;

	private final StoreWordTemplate wordTemplate;



	@SneakyThrows
	public boolean saveTemplate(Template template, MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String extName = FileUtil.extName(originalFilename);
		Assert.isTrue(StrUtil.equalsIgnoreCase("docx", extName), "模板文件仅支持后缀docx");
		String excelFilename = IdUtil.getSnowflakeNextIdStr() + ".xlsx";
		String profile = RelaxedConfig.getProfile();
		FileMeta fileMeta = FileUtils.upload(profile, LoanUploadPath.TEMPLATE_WORD_RELATIVE_PATH, file, FileConfig.create().splitDate(false));
		String docFile = fileMeta.getLocalFullFilePath();
		String docUrl = fileMeta.getRelativeFilePath();
		// 2.生成数据文件
		File docfilePath = new File(docFile);
		List<ElementMeta> elementMetas = wordTemplate.templateElement(new FileInputStream(docfilePath));
		List<List<String>> headList = new ArrayList<>();
		headList.addAll(elementMetas.stream().map(e -> {
			String tagName = e.getTagName();
			List<String> names = new ArrayList<>(1);
			names.add(tagName);
			return names;
		}).collect(Collectors.toList()));
		//相对路径
		String excelDir = "/"+ LoanUploadPath.TEMPLATE_EXCEL_RELATIVE_PATH;
		//根路径+相对路径 =excel存储目录
		String excelParentDir=RelaxedConfig.getProfile()+excelDir;
		//目录检查
		FileUtil.mkdir(excelParentDir);
		//相对文件路径
		String relativePath = excelDir +"/"+ excelFilename;

		EasyExcel.write(RelaxedConfig.getProfile()+relativePath).head(headList).inMemory(true).sheet().doWrite(ListUtil.empty());
		template.setTemplateFilename(originalFilename);
		template.setTemplateUrl(docUrl);
		template.setDatafilePath(relativePath);
		template.setTemplateCode(IdUtil.getSnowflakeNextIdStr() + "");
		// 模板填充域
		List<TemplateArea> templateAreas = new ArrayList<>();
		for (ElementMeta elementMeta : elementMetas) {
			TemplateArea templateArea = new TemplateArea();
			templateArea.setTextArea(elementMeta.getSource());
			templateArea.setTagName(elementMeta.getTagName());
			templateArea.setCreatedTime(LocalDateTime.now());
			templateArea.setUpdatedTime(LocalDateTime.now());
			templateAreas.add(templateArea);
		}
		return Boolean.TRUE.equals(transactionTemplate.execute(status -> {
			templateService.save(template);
			for (TemplateArea templateArea : templateAreas) {
				templateArea.setTemplateId(template.getTemplateId());
			}
			templateAreaService.insertBatchSomeColumn(templateAreas);
			return Boolean.TRUE;
		}));

	}

}
