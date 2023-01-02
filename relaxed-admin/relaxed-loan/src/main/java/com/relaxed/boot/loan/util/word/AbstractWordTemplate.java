package com.relaxed.boot.loan.util.word;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.template.ElementTemplate;
import com.deepoove.poi.template.MetaTemplate;
import com.deepoove.poi.util.PoitlIOUtils;
import com.relaxed.boot.loan.util.PdfUtil;
import com.relaxed.boot.loan.util.word.domain.ElementMeta;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yakir
 * @Topic AbstractWordTemplate
 * @Description
 * @date 2022/4/24 11:39
 * @Version 1.0
 */
public abstract class AbstractWordTemplate implements IWordTemplate {

	@Override
	public <V> void renderWord(InputStream inputStream, OutputStream outputStream, Map<String, V> dataMap,
			Configure configure) {
		XWPFTemplate xwpfTemplate = getXwpfTemplate(inputStream, configure);
		XWPFTemplate template = xwpfTemplate.render(dataMap);
		try {
			template.write(outputStream);
			outputStream.flush();
		}
		catch (IOException e) {
			throw new WordException(e);
		}
		finally {
			PoitlIOUtils.closeQuietlyMulti(xwpfTemplate, outputStream);
		}
	}

	@Override
	public <V> void renderPdf(InputStream inputStream, OutputStream outputStream, Map<String, V> dataMap,
			Configure configure) {
		ByteArrayOutputStream tmporaryArrayOutputStream = new ByteArrayOutputStream();
		renderWord(inputStream, tmporaryArrayOutputStream, dataMap, configure);
		ByteArrayInputStream tmporaryWordInputStream = new ByteArrayInputStream(
				tmporaryArrayOutputStream.toByteArray());
		convertToPdf(tmporaryWordInputStream, outputStream);
	}

	@Override
	public <R> R render(InputStream inputStream, Configure configure, RenderFunction<R> function) {
		XWPFTemplate xwpfTemplate = getXwpfTemplate(inputStream, configure);
		try {
			return function.render(xwpfTemplate);
		}
		finally {
			PoitlIOUtils.closeQuietlyMulti(xwpfTemplate);
		}
	}

	@Override
	public List<ElementMeta> templateElement(InputStream inputStream, Configure configure) {
		RenderFunction<List<ElementMeta>> renderFunction = xwpfTemplate -> {
			List<MetaTemplate> elementTemplates = xwpfTemplate.getElementTemplates();
			return convertToElementMetas(elementTemplates);
		};
		return render(inputStream, configure, renderFunction);
	}

	/**
	 * 将获取到去重后的word模板元素
	 * @author yakir
	 * @date 2022/4/18 17:21
	 * @param elementTemplates
	 * @return java.util.List<com.relaxed.third.util.word.domain.ElementMeta>
	 */
	protected List<ElementMeta> convertToElementMetas(List<MetaTemplate> elementTemplates) {
		List<ElementMeta> elementMetas = new ArrayList<>();
		Set<String> elementNames = new HashSet<>();
		Integer i = 0;
		for (MetaTemplate metaTemplate : elementTemplates) {
			if (metaTemplate instanceof ElementTemplate) {
				ElementTemplate elementTemplate = (ElementTemplate) metaTemplate;
				String tagName = elementTemplate.getTagName();
				String source = elementTemplate.getSource();
				if (elementNames.contains(tagName)) {
					continue;
				}
				elementNames.add(tagName);
				elementMetas.add(new ElementMeta(i++, tagName, source));
			}
		}
		return elementMetas;
	}

	/**
	 * word 转pdf
	 * @param inputStream
	 * @param outputStream
	 */
	public void convertToPdf(InputStream inputStream, OutputStream outputStream) {
		// PdfUtil.doc2pdf(inputStream, outputStream);
	}

	/**
	 * 获取实例
	 * @author yakir
	 * @date 2022/4/24 11:41
	 * @param inputStream
	 * @param configure
	 * @return com.deepoove.poi.XWPFTemplate
	 */
	protected XWPFTemplate getXwpfTemplate(InputStream inputStream, Configure configure) {
		return XWPFTemplate.compile(inputStream, configure);
	}

}
