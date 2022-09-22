package web.chat.anonymousChat.controllers;

import liquibase.pro.packaged.S;
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
        JSONObject toReturn;

        switch (json.getString("status")){
            case "new_user" -> toReturn = handleNewUser();
            default -> throw new IllegalStateException("Unexpected value: " + json.getString("status"));
        }


        return toReturn.toString();
    }

    private JSONObject handleNewUser(){
        String uuid = UUID.randomUUID().toString();

        UserListDAO userListDAO = new UserListDAO(0, uuid, UserStatusEnum.Free);

        userListRepository.save(userListDAO);

        JSONObject toReturn = new JSONObject();
        toReturn.put("type", "new_user_id");
        toReturn.put("user_id", uuid);

        return toReturn;
    }

}
