package com.tangshengbo.controller;

import com.tangshengbo.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.RedisClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/26.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RedisService redisService;

    /**
     * 缓存测试
     */
    @RequestMapping("/redisTest/{redisValue}")
    public String redisTest(@PathVariable String redisValue) {
        String result = "";
        try {
            redisTemplate.opsForValue().set("test-key", redisValue, 5, TimeUnit.SECONDS);// 缓存有效期2秒

            logger.info("从Redis中读取数据：" + redisTemplate.opsForValue().get("test-key").toString());

            TimeUnit.SECONDS.sleep(3);

            result = (String) redisTemplate.opsForValue().getAndSet("test-key","唐波");
            logger.info("等待3秒后尝试读取过期的数据:" + redisTemplate.opsForValue().get("test-key"));

            logger.info("等待3秒后尝试读取过期的数据：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/redisCache")
    public String redisCache() {
        redisClient.set("shanhy", "hello,shanhy", 100);
        logger.info("getRedisValue = {}", redisClient.get("shanhy"));
        logger.info("cacheByAutoKey = {}" + redisService.cacheByAutoKey("aaa", "bbb"));
        return redisService.cache();
    }
}
