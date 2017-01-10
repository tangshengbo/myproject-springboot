package com.tangshengbo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Administrator on 2016/12/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Value("${server.port}")
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void testGetUser() {

        logger.info("port:{}", port);

        String result = restTemplate.getForObject("http://localhost:" + port + "/user/getuser/1", String.class);

        logger.info("result:{}", result);

    }

    @Test
    public void testGetUserName() {

        logger.info("port:{}", port);

        String result = restTemplate.getForObject("http://localhost:" + port + "/user/name", String.class);

        logger.info("result:{}", result);

    }


}
