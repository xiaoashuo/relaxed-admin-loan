package com.relaxed.boot.risk.core.distributor.engine.handles;

import cn.hutool.json.JSONUtil;

import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeEnum;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeHandle;
import com.relaxed.boot.risk.core.distributor.subscribe.SubscribeType;
import com.relaxed.boot.risk.enums.CacheKey;
import com.relaxed.boot.risk.model.vo.RiskFieldVO;
import com.relaxed.common.redis.RedisHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Yakir
 * @Topic SubscribeModelHandle
 * @Description 订阅model
 * @date 2021/8/29 9:34
 * @Version 1.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class SubscribeFieldHandle implements SubscribeHandle {

	@Override
	public SubscribeType type() {
		return SubscribeEnum.PUB_SUB_FIELD_CHANNEL;
	}

	@Override
	public void onMessage(String channel, String message) {
		log.info("model field update message:{}", message);
		RiskFieldVO vo = JSONUtil.toBean(message, RiskFieldVO.class);
		// 删除本地缓存的规则模型
		RedisHelper.del(CacheKey.getModelFieldCacheKey(vo.getModelId()));
	}

}
