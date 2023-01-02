package com.relaxed.boot.loan.util.word.provider;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LocalWordTemplateProvider implements WordTemplateProvider<String> {

	public static final String WORD_TEMPLATE_KEY = "classpath*:word/template/**.docx";

	private Map<String, String> TEMPLATE_HOLDER = new HashMap<>();

	private static final PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	@SneakyThrows

	public void init() {
		// 初始化项目模板
		Resource[] resources = resolver.getResources(WORD_TEMPLATE_KEY);
		for (Resource resource : resources) {
			String filename = resource.getFilename();
			String absolutePath = resource.getFile().getAbsolutePath();
			TEMPLATE_HOLDER.put(filename, absolutePath);
		}
	}

	@Override
	public String get(String templateCode) {
		return TEMPLATE_HOLDER.get(templateCode);
	}

	@SneakyThrows
	@Override
	public InputStream provide(String templateCode) {
		String templatePath = TEMPLATE_HOLDER.get(templateCode);
		Assert.hasText(templateCode, "template code can not empty.");
		Assert.hasText(templatePath, "template path can not empty.");
		return new FileInputStream(templatePath);
	}

}
