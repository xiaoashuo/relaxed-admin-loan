package com.relaxed.boot.risk.core.distributor.subscribe;

/**
 * @author Yakir
 * @Topic SubscribeType
 * @Description 订阅者类型
 * @date 2021/9/11 11:06
 * @Version 1.0
 */
public interface SubscribeType {

	/**
	 * 获取订阅者类型渠道
	 * @author yakir
	 * @date 2021/9/11 11:07
	 * @return java.lang.String
	 */
	String getChannel();

	/**
	 * 获取订阅者类型描述
	 * @author yakir
	 * @date 2021/9/11 11:07
	 * @return java.lang.String
	 */
	String getDesc();

}
