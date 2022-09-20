package web.chat.anonymousChat.controllers;

import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import web.chat.anonymousChat.DAOs.UserListDAO;
import web.chat.anonymousChat.UserStatusEnum;
import web.chat.anonymousChat.repositories.UserListRepository;

import java.util.UUID;


@Controller
public class MessageController {

    private final UserListRepository userListRepository;

    public MessageController(UserListRepository userListRepository) {
        this.userListRepository = userListRepository;
    }

    @MessageMapping("/hello")
    @SendTo("/message-channel/content")
    public String doMessaging(@Payload String body){
        JSONObject json = new JSONObject(body);

        switch (json.getString("status")){
            case "new_user" -> handleNewUser();
        }



        return body;
    }

    private void handleNewUser(){
        UserListDAO userListDAO = new UserListDAO(0,UUID.randomUUID().toString(), UserStatusEnum.Free);
        userListRepository.save(userListDAO);
    }

}
