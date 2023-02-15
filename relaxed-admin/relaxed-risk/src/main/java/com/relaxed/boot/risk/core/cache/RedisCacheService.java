package com.relaxed.boot.risk.core.cache;

import com.relaxed.common.redis.RedisHelper;

/**
 * @author Yakir
 * @Topic RedisCacheService
 * @Description
 * @date 2023/2/15 10:41
 * @Version 1.0
 */
public class RedisCacheService implements CacheService {
    @Override
    public String  get(String key) {
        return RedisHelper.get(key);
    }

    @Override
    public void put(String key, String obj, Long expireTime) {
        RedisHelper.set(key,obj,expireTime);
    }

    @Override
    public void del(String key) {
        RedisHelper.del(key);
    }
}
