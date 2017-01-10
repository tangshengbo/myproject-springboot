package com.tangshengbo.controller;

import com.tangshengbo.model.RequestMessage;
import com.tangshengbo.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by tangshengbo on 2017/1/10.
 */
@Controller
public class WsController {

    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        logger.info("RequestMessage:{}", message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }
}
