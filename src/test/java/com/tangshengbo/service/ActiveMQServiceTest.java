package com.tangshengbo.service;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tang on 2017/6/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActiveMQServiceTest {

    private static Logger logger = LoggerFactory.getLogger(ActiveMQServiceTest.class);

    @Autowired
    private ProducerService producerService;

    @Test
    public void testMQ(){
        Destination destination = new ActiveMQQueue("producer.queue");
        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producerService.sendMessage(destination, "tangshengbo\t" + i);
        }
    }
}


