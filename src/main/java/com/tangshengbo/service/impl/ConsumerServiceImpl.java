package com.tangshengbo.service.impl;

import com.tangshengbo.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Tang on 2017/6/30.
 */
@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    private static Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

//    @SendTo("consumer.queue")
//    @JmsListener(destination = "producer.queue")
    @Override
    public String receiveQueue(String message) {
        logger.info("接收producer.queue发送的消息:{}", message);
        return "consumer" + "\t" + message;
    }
}
