package com.relaxed.boot.risk.core.distributor.subscribe;

/**
 * @author Yakir
 * @Topic SubscribeHandle
 * @Description
 * @date 2021/8/29 9:30
 * @Version 1.0
 */
public interface SubscribeHandle {

	/**
	 * 当前订阅者类型
	 * @author yakir
	 * @date 2021/8/29 9:58
	 * @return com.relaxed.common.risk.engine.core.distributor.subscribe.SubscribeType
	 */
	SubscribeType type();

	/**
	 * 消息订阅接收者
	 * @author yakir
	 * @date 2021/8/29 9:31
	 * @param channel
	 * @param message
	 */
	void onMessage(String channel, String message);

}
