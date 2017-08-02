package com.tangshengbo.service.impl;

import com.tangshengbo.service.RedisService;
import com.tangshengbo.util.RedisKeys;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * Created by Tang on 2017/6/26.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private final RedisSerializer<String> stringSerializer = new StringRedisSerializer();

    @Cacheable(value = RedisKeys._CACHE_TEST, key = "'" + RedisKeys._CACHE_TEST + "'")
    @Override
    public String cache() {
        return RandomStringUtils.randomNumeric(4);
    }

    /**
     * 存储在Redis中的key自动生成，生成规则详见CachingConfig.keyGenerator()方法
     */
    @Cacheable()
    @Override
    public String cacheByAutoKey(String k1, String k2) {
        return RandomStringUtils.randomNumeric(4);
    }

    @Override
    public void del(final String key) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) {
                return connection.del(rawKey(key));
            }
        });
    }

    @Override
    public void set(final String key, final String value, final long timeOut) {
        this.set(rawKey(key), rawKey(value), timeOut);
    }

    @Override
    public void set(final String key, final String value) {
        this.set(key, value, 0L);
    }

    @Override
    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public String doInRedis(RedisConnection redisConnection) {
                return stringOf(redisConnection.get(rawKey(key)));
            }
        });
    }

    @Override
    public boolean setNX(final String key, final String value) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) {
                return redisConnection.setNX(rawKey(key), rawKey(value));
            }
        });
    }

    @Override
    public boolean setNX(final String key, final String value, final long timeOut) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) {
                byte[] lockKey = rawKey(key);
                boolean isSuccess = redisConnection.setNX(lockKey, rawKey(value));
                if (isSuccess && timeOut > 0) {
                    redisConnection.expire(lockKey, timeOut);
                }
                return isSuccess;
            }
        });
    }

    @Override
    public boolean exists(final String key) {
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) {
                return redisConnection.exists(rawKey(key));
            }
        });
    }

    @Override
    public long ttl(final String key) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) {
                return redisConnection.ttl(rawKey(key));
            }
        });
    }

    @Override
    public long dbSize() {
        return (Long) redisTemplate.execute(new RedisCallback() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) {
                return redisConnection.dbSize();
            }
        });
    }

    @Override
    public String ping() {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public String doInRedis(RedisConnection redisConnection) {
                return redisConnection.ping();
            }
        });
    }

    private final byte[] rawKey(final String key) {
        return stringSerializer.serialize(key);
    }

    private final String stringOf(byte[] value) {
        if (value == null) {
            return "";
        }
        return stringSerializer.deserialize(value);
    }

    private void set(final byte[] key, final byte[] value, final long timeOut) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) {
                if (timeOut > 0) {
                    connection.setEx(key, timeOut, value);
                } else {
                    connection.set(key, value);
                }
                return timeOut;
            }
        });
    }
}
