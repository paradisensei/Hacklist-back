package org.hacklist.service.impl;

import org.hacklist.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
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
        redisTemplate.execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return null;
            }
        });
    }

}
