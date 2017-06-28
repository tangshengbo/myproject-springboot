package com.tangshengbo.service;

import com.tangshengbo.ApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Tang on 2017/6/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisServiceTest {

    private static Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired
   private RedisService redisService;

    @Test
    public void testPing() {
        String result = redisService.ping();
        logger.info("Ping:{}", result);
    }

    @Test
    public void testDBSize() {
        Long size = redisService.dbSize();
        logger.info("size:{}", size);
    }

}
