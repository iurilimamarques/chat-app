package com.iurimarques.apichatproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.iurimarques.apichatproject.model.Message;
import com.iurimarques.apichatproject.model.OutputMessage;

@Controller
public class MessageController {
    
    //@MessageMapping("/gs-guide-websocket")
    //@SendTo("/chat/message")
    //public OutputMessage message(Message message) throws Exception {
    //    String time = new SimpleDateFormat("HH:mm").format(new Date());
    //    return new OutputMessage(message.getFrom(), message.getMessage(), time);
    //}
}
