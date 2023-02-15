package com.relaxed.boot.risk.core.distributor.engine;

import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeHandle;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeType;
import com.relaxed.common.core.util.SpringUtils;

import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yakir
 * @Topic SubscribeHolder
 * @Description
 * @date 2021/8/29 9:56
 * @Version 1.0
 */
public class SubscribeHolder implements InitializingBean {

	/**
	 * 订阅者处理器
	 */
	private static final Map<String, SubscribeHandle> SUBSCRIBE_HOLDER = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, SubscribeHandle> subscribeHandleMap = SpringUtils.getBeansOfType(SubscribeHandle.class);
		for (SubscribeHandle value : subscribeHandleMap.values()) {
			SUBSCRIBE_HOLDER.put(value.type().getChannel(), value);
		}
	}

	/**
	 * 根据渠道获取订阅者
	 * @author yakir
	 * @date 2021/8/29 10:02
	 * @param subscribeType
	 * @return com.relaxed.common.risk.engine.core.distributor.subscribe.SubscribeHandle
	 */
	public static SubscribeHandle getByChannel(SubscribeType subscribeType) {
		return getByChannel(subscribeType.getChannel());
	}

	/**
	 * 根据渠道获取订阅者
	 * @author yakir
	 * @date 2021/8/29 10:04
	 * @param channel
	 * @return com.relaxed.common.risk.engine.core.distributor.subscribe.SubscribeHandle
	 */
	public static SubscribeHandle getByChannel(String channel) {
		return SUBSCRIBE_HOLDER.get(channel);
	}

}
