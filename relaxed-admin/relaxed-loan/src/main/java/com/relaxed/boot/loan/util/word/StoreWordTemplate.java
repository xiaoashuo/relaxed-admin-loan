package com.relaxed.boot.loan.util.word;

import com.deepoove.poi.config.Configure;

import com.relaxed.boot.loan.util.word.domain.ElementMeta;
import com.relaxed.boot.loan.util.word.provider.WordTemplateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Yakir
 * @Topic StoreWordTemplate
 * @Description 带模板存储的wordTemplate
 * @date 2022/4/24 12:03
 * @Version 1.0
 */
@RequiredArgsConstructor
public class StoreWordTemplate extends AbstractWordTemplate {

	private final Configure configure;

	private final WordTemplateProvider wordTemplateProvider;

	public Configure getConfigure() {
		return configure;
	}

	public <V> void renderWord(String templateCode, OutputStream outputStream, Map<String, V> dataMap) {
		InputStream inputStream = getInputStream(templateCode);
		super.renderWord(inputStream, outputStream, dataMap);
	}

	private InputStream getInputStream(String templateCode) {
		InputStream inputStream = wordTemplateProvider.provide(templateCode);
		Assert.notNull(inputStream, "origin InputStream can not be null.");
		return inputStream;
	}

	public <V> void renderPdf(String templateCode, OutputStream outputStream, Map<String, V> dataMap) {
		InputStream inputStream = getInputStream(templateCode);
		super.renderPdf(inputStream, outputStream, dataMap);
	}

	public List<ElementMeta> templateElement(String templateCode) {
		InputStream inputStream = getInputStream(templateCode);
		return super.templateElement(inputStream);
	}

}
