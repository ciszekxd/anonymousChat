package web.chat.anonymousChat.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/message-channel/content")
    public String doMessaging(@Payload String body){
        System.out.println(body);
        return body;
    }

}
