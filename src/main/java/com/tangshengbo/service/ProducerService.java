package com.tangshengbo.service;


import javax.jms.Destination;

/**
 * Created by Tang on 2017/6/30.
 */
public interface ProducerService {

    void sendMessage(Destination destination, final String message);

    void consumerMessage(String message);
}
