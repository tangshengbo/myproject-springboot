package com.tangshengbo.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tang on 2017/6/26.
 */
@Component
public class RedisKeys {

    // 测试 begin
    public static final String _CACHE_TEST = "_cache_test";// 缓存key
    public static final Long _CACHE_TEST_SECOND = 5L;// 缓存时间
    // 测试 end

    // 根据key设定具体的缓存时间
    private Map<String, Long> expiresMap = null;

    @PostConstruct
    public void init(){
        expiresMap = new HashMap<>();
        expiresMap.put(_CACHE_TEST, _CACHE_TEST_SECOND);
    }

    public Map<String, Long> getExpiresMap(){
        return this.expiresMap;
    }
}
