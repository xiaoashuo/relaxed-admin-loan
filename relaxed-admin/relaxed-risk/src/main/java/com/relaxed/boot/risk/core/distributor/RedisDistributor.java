package com.relaxed.boot.risk.core.distributor;



import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeHandle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Yakir
 * @Topic RedisDistributor
 * @Description
 * @date 2021/8/29 9:48
 * @Version 1.0
 */
@RequiredArgsConstructor
@Slf4j
public class RedisDistributor implements EventDistributor {

	private final StringRedisTemplate stringRedisTemplate;

	@Override
	public void distribute(String channel, String message) {
		log.info("distribute ! channel={},message={}", channel, message);
		stringRedisTemplate.convertAndSend(channel, message);
	}

	@Override
	public void subscribe(String channel, SubscribeHandle subscribeHandle) {
		log.info("subscribe ! channel={}", channel);
		stringRedisTemplate.getConnectionFactory().getConnection().subscribe((msg, pat) -> {
			byte[] bytes = msg.getBody();
			subscribeHandle.onMessage(channel, new String(bytes));
		}, channel.getBytes());
		log.info("subscribe success channel={}", channel);
	}

}
