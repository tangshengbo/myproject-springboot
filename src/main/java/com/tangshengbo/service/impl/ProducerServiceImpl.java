package com.tangshengbo.service.impl;

import com.tangshengbo.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * Created by Tang on 2017/6/30.
 */
@Service("producerService")
public class ProducerServiceImpl implements ProducerService {

    private static Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

    /**
     * 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
     */
    @Autowired
    private JmsMessagingTemplate messagingTemplate;

    /**
     * 发送消息，destination是发送到的队列，message是待发送的消息
     * @param destination
     * @param message
     */
    @Override
    public void sendMessage(Destination destination, String message) {
        logger.info("producer发送的消息:{}", message);
        messagingTemplate.convertAndSend(destination, message);
    }

//    @JmsListener(destination = "consumer.queue")
    @Override
    public void consumerMessage(String message) {
        logger.info("接收consumer.queue发送的消息:{}", message);
    }
}
