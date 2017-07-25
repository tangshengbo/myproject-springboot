package com.tangshengbo.controller;

import com.tangshengbo.service.RedisService;
import com.tangshengbo.util.DistributeLockHandler;
import com.tangshengbo.util.Lock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/26.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private DistributeLockHandler lockHandler;

    @RequestMapping("/redisCache/{key}/{value}/{timeOut}")
    public String redisCache(@PathVariable("key") String key, @PathVariable("value") String value,
                             @PathVariable("timeOut") String timeOut) {
        boolean valid;
        valid =redisService.setNX(key, value, Long.parseLong(timeOut));
        logger.info("valid:{}", valid);
        logger.info("获得数据:{}", redisService.get(key));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        valid = redisService.setNX(key, key + value);
        logger.info("valid:{}", valid);
        logger.info("获得数据:{}", redisService.get(key));
        logger.info("还剩多少存活时间:{}", redisService.ttl(key));
        return redisService.get(key);
    }

    @RequestMapping("/uid")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        logger.info("sessionId:{}", uid);
        session.setAttribute("uid", uid);
        return session.getId();
    }

    @RequestMapping(value = "/lock", method = RequestMethod.GET)
    public String getLock(String key, String value) {
        Lock lock = new Lock(key,value);
        logger.info("getLock:{}", lock.toString());
        boolean result = lockHandler.tryLock(lock);
        return result? " success" : "failure";
   }
}
