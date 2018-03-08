package com.example.websocketexample.controller;

import com.example.websocketexample.model.Greeting;
import com.example.websocketexample.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/notification")
    @SendTo("/topic/greetings")
    // That will got all subscribers
    public Greeting notification(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getValue() + "!");
    }

    @MessageMapping("/monologue")
    @SendToUser("/queue/individual/message")
    // That will got just message sender
    public Greeting monologue(HelloMessage message) {
        return new Greeting("Hello, " + message.getValue() + "!");
    }

    @MessageMapping("/dialog")
//    @SendToUser("/personal")
    // That will got recipient
    public void messageByUsername(HelloMessage message/*, Principal principal*/) {
        template.convertAndSendToUser(message.getValue(), "/personal", new Greeting("Hello, " + message.getValue() + "!"));
    }

}
