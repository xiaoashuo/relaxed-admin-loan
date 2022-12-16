package com.relaxed.boot.loan.util.word;

import com.deepoove.poi.config.Configure;
import com.relaxed.boot.loan.util.word.domain.ElementMeta;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Yakir
 * @Topic IWordTemplate
 * @Description
 * @date 2022/4/24 11:39
 * @Version 1.0
 */
public interface IWordTemplate {

	/**
	 * 默认配置 占位符 {{}}
	 */
	Configure DEFAULT_CONFIGURE = Configure.createDefault();

	/**
	 * 渲染word使用默认配置
	 * @author yakir
	 * @date 2022/4/24 12:13
	 * @param inputStream
	 * @param outputStream
	 * @param dataMap
	 */
	default <V> void renderWord(InputStream inputStream, OutputStream outputStream, Map<String, V> dataMap) {
		renderWord(inputStream, outputStream, dataMap, getConfigure());
	}

	/**
	 * 渲染pdf
	 * @author yakir
	 * @date 2022/4/24 12:16
	 * @param inputStream
	 * @param outputStream
	 * @param dataMap
	 */
	default <V> void renderPdf(InputStream inputStream, OutputStream outputStream, Map<String, V> dataMap) {
		renderPdf(inputStream, outputStream, dataMap, getConfigure());
	}

	/**
	 * 模板元素
	 * @author yakir
	 * @date 2022/4/24 12:16
	 * @param inputStream
	 * @return java.util.List<com.relaxed.third.util.word.domain.ElementMeta>
	 */
	default List<ElementMeta> templateElement(InputStream inputStream) {
		return templateElement(inputStream, getConfigure());
	}

	/**
	 * 渲染word
	 * @author yakir
	 * @date 2022/4/24 12:00
	 * @param inputStream 源文件输入流
	 * @param outputStream 目标文件输出流
	 * @param dataMap 数据参数
	 * @param configure 配置
	 */
	<V> void renderWord(InputStream inputStream, OutputStream outputStream, Map<String, V> dataMap,
			Configure configure);

	/**
	 * 渲染PDF
	 * @author yakir
	 * @date 2022/4/24 12:01
	 * @param inputStream
	 * @param outputStream
	 * @param dataMap
	 * @param configure
	 */
	<V> void renderPdf(InputStream inputStream, OutputStream outputStream, Map<String, V> dataMap, Configure configure);

	/**
	 * 获取去重后所有占位符参数
	 * @author yakir
	 * @date 2022/4/24 12:02
	 * @param inputStream 源文件输入流
	 * @param configure 配置
	 * @return java.util.List<com.relaxed.third.util.word.domain.ElementMeta>
	 */
	List<ElementMeta> templateElement(InputStream inputStream, Configure configure);

	/**
	 * 自定义渲染函数
	 * @author yakir
	 * @date 2022/4/24 12:02
	 * @param inputStream 源文件输入流
	 * @param configure 配置
	 * @param function 功能函数
	 * @return R
	 */
	<R> R render(InputStream inputStream, Configure configure, RenderFunction<R> function);

	/**
	 * 得到默认配置
	 * @author yakir
	 * @date 2022/4/24 12:14
	 * @return com.deepoove.poi.config.Configure
	 */
	default Configure getConfigure() {
		return DEFAULT_CONFIGURE;
	};

}
