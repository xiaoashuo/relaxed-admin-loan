package com.relaxed.boot.risk.config;

import com.relaxed.boot.risk.core.distributor.EventDistributor;
import com.relaxed.boot.risk.core.distributor.RedisDistributor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Yakir
 * @Topic DistributorConfiguration
 * @Description
 * @date 2021/9/11 11:04
 * @Version 1.0
 */
@Configuration(proxyBeanMethods = false)
public class DistributorConfiguration {

	/**
	 * 事件分发者
	 * @author yakir
	 * @date 2021/8/29 10:09
	 * @param stringRedisTemplate
	 * @return com.relaxed.common.risk.engine.core.distributor.EventDistributor
	 */
	@Bean
	@ConditionalOnMissingBean
	public EventDistributor eventDistributor(StringRedisTemplate stringRedisTemplate) {
		return new RedisDistributor(stringRedisTemplate);
	}

}
