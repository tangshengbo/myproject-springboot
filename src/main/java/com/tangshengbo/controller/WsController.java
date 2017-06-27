package com.tangshengbo.controller;

/**
 * Created by tangshengbo on 2017/1/10.
 */
//@Controller
public class WsController {

   /* private static Logger logger = LoggerFactory.getLogger(HelloController.class);

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
    }*/
}
