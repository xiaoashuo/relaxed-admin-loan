package com.relaxed.boot.risk.core.distributor.engine;


import com.relaxed.boot.risk.core.distributor.EventDistributor;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Yakir
 * @Topic SubscribeRegister
 * @Description
 * @date 2021/8/29 9:26
 * @Version 1.0
 */
@Slf4j
@RequiredArgsConstructor
public class SubscribeRegister implements InitializingBean {

	private final EventDistributor eventDistributor;

	@Override
	public void afterPropertiesSet() throws Exception {
		registerSubscribe();
	}

	private void registerSubscribe() {
		// 注册所有订阅者
		for (SubscribeEnum value : SubscribeEnum.values()) {
			String channel = value.getChannel();
			eventDistributor.subscribe(channel, SubscribeHolder.getByChannel(channel));

		}
	}

}
