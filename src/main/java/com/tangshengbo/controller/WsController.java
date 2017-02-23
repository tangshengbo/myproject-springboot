package com.tangshengbo.controller;

import com.tangshengbo.model.RequestMessage;
import com.tangshengbo.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by tangshengbo on 2017/1/10.
 */
@Controller
public class WsController {

    private static Logger logger = LoggerFactory.getLogger(WsController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        logger.info("RequestMessage:{}", message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }


    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if (principal.getName().equals("user")) {
            messagingTemplate.convertAndSendToUser("admin", "/queue/notifications", principal.getName() + "给您发来了消息：" + msg);
        } else {
            messagingTemplate.convertAndSendToUser("user", "/queue/notifications", principal.getName() + "给您发来了消息：" + msg);
        }
    }
}
