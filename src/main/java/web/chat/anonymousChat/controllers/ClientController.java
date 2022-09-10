package web.chat.anonymousChat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connection")
public class ClientController {
    @GetMapping
    public String sayHello(){
        return "Hello World!";
    }
}
