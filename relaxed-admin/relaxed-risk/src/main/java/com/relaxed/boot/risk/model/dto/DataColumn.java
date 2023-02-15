package com.relaxed.boot.risk.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Yakir
 * @Topic DataColumn
 * @Description
 * @date 2021/9/26 13:51
 * @Version 1.0
 */
@Data
public class DataColumn {

	/**
	 * 标签
	 */
	private String label;

	/**
	 * 值
	 */
	private String value;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 子数据列
	 */
	List<DataColumn> children;

	public DataColumn() {
	}

	public DataColumn(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public DataColumn(String label, String value, String type) {
		this.label = label;
		this.value = value;
		this.type = type;
	}

}
