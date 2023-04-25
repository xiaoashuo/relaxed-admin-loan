package com.relaxed.boot.loan.util;

import cn.hutool.core.util.IdUtil;

/**
 * @author Yakir
 * @Topic ProNoUtil
 * @Description
 * @date 2023/1/16 17:12
 * @Version 1.0
 */
public class ProNoUtil {

	public static String generateTradeNo() {
		return "trade:" + IdUtil.getSnowflakeNextIdStr();
	}

}
