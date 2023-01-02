package com.relaxed.boot.loan.util.word.provider;

import java.io.InputStream;

public interface WordTemplateProvider<V> {

	/**
	 * 根据模板code获取值
	 * @author yakir
	 * @date 2022/4/16 16:32
	 * @param templateCode
	 * @return V
	 */
	V get(String templateCode);

	/**
	 * 根据模板Code 获取原始Word数据流
	 * @author yakir
	 * @date 2022/4/16 16:15
	 * @param templateCode
	 * @return java.io.InputStream
	 */
	InputStream provide(String templateCode);

}
