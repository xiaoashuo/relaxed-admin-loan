package com.relaxed.boot.risk.model.dto;

import lombok.Data;

/**
 * @author Yakir
 * @Topic Risk
 * @Description
 * @date 2021/8/31 14:33
 * @Version 1.0
 */
@Data
public class Risk {

	/**
	 * 风控结果
	 */
	private String risk;

	/**
	 * 分数
	 */
	private Integer score;

}
