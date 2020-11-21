package com.me.challenge.authorization.infra.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCacheService<String, V> {

    @Autowired
    protected RedisTemplate<String, V> redisTemplate;


    protected V getCacheValue(final String key) {
        return redisTemplate.opsForValue().getOperations().opsForValue().get(key);
    }

    public void persist(final String key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void persistWithTtl(final String key, V value, TimeUnit timeUnit, long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }


}
