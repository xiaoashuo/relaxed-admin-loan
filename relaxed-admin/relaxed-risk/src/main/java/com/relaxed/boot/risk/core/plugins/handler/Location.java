package com.relaxed.boot.risk.core.plugins.handler;

import lombok.Data;

/**
 * 区域
 *
 * @author Yakir
 */
@Data
public class Location {

	private String country = "中国";

	private String region = "";

	private String province = "";

	private String city = "";

	private String address = "";

}
