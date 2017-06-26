package com.tangshengbo.service.impl;

import com.tangshengbo.service.RedisService;
import com.tangshengbo.utils.RedisKeys;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Tang on 2017/6/26.
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Cacheable(value = RedisKeys._CACHE_TEST, key = "'" + RedisKeys._CACHE_TEST + "'")
    @Override
    public String cache() {
        return RandomStringUtils.randomNumeric(4);
    }

    /**
     * 存储在Redis中的key自动生成，生成规则详见CachingConfig.keyGenerator()方法
     */
    @Cacheable(value = RedisKeys._CACHE_TEST)
    @Override
    public String cacheByAutoKey(String k1, String k2) {
        return RandomStringUtils.randomNumeric(4);
    }
}
