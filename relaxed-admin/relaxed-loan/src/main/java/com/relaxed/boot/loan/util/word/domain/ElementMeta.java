package com.relaxed.boot.loan.util.word.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ElementMeta {

	/**
	 * 索引
	 */
	private Integer index;

	/**
	 * 仅名称
	 */
	private String tagName;

	/**
	 * 源文本
	 */
	private String source;

}
