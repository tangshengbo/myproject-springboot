package com.tangshengbo.service;

/**
 * Created by Tang on 2017/6/26.
 */
public interface RedisService {

    String cache();

    String cacheByAutoKey(String k1, String k2);
}
