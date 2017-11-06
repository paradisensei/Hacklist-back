package org.hacklist.service.impl;

import org.hacklist.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate redisTemplate;

    @Autowired
    public CacheServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void flush() {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.flushDb();
            return null;
        });
    }

}
