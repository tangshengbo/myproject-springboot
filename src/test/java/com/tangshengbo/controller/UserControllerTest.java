package com.tangshengbo.controller;

import com.tangshengbo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Administrator on 2016/12/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@WebAppConfiguration // 使用@WebIntegrationTest注解需要将@WebAppConfiguration注释掉
@WebIntegrationTest("server.port:8089")// 使用0表示端口号随机，也可以具体指定如8888这样的固定端口
public class UserControllerTest {

    private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Value("${server.port}")
    private int port;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetUser() {

        logger.info("port:{}",port);

        String result = restTemplate.getForObject("http://localhost:"+port+"/user/getuser/1", String.class);

        logger.info("result:{}", result);

    }

    @Test
    public void testGetUserName() {

        logger.info("port:{}",port);

        String result = restTemplate.getForObject("http://localhost:"+port+"/user/name", String.class);

        logger.info("result:{}", result);

    }


}
