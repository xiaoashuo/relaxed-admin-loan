package com.relaxed.boot.loan.config.word;

import cn.hutool.core.collection.CollectionUtil;

import com.relaxed.boot.loan.model.entity.Template;
import com.relaxed.boot.loan.service.TemplateService;
import com.relaxed.boot.loan.util.word.provider.WordTemplateProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.Assert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class RemoteWordTemplateProvider implements WordTemplateProvider<String> {

	private final TemplateService templateService;

	@Override
	public String get(String templateCode) {
		return templateService.getByTemplateCode(templateCode);
	}

	@SneakyThrows
	@Override
	public InputStream provide(String templateCode) {
		String templatePath = get(templateCode);
		Assert.hasText(templateCode, "template code can not empty.");
		Assert.hasText(templatePath, "template path can not empty.");
		return new FileInputStream(templatePath);
	}

}
