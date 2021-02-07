package com.iurimarques.apichatproject.controller;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.iurimarques.apichatproject.converter.DozerConverter;
import com.iurimarques.apichatproject.data.vo.MessageVO;
import com.iurimarques.apichatproject.model.Message;
import com.iurimarques.apichatproject.model.User;
import com.iurimarques.apichatproject.repository.MessageRepository;
import com.iurimarques.apichatproject.repository.UserRepository;
import com.iurimarques.apichatproject.utils.ActiveUserChangeListener;
import com.iurimarques.apichatproject.utils.ActiveUserManager;
import com.iurimarques.apichatproject.utils.MessageSorter;

@RestController
@RequestMapping("api/api-chat/message")
public class MessageController implements ActiveUserChangeListener {

    @Autowired
    private SimpMessagingTemplate webSocket;
 
    @Autowired
    private ActiveUserManager activeUserManager;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;
 
    @PostConstruct
    private void init() {
        activeUserManager.registerListener(this);
    }
 
    @PreDestroy
    private void destroy() {
        activeUserManager.removeListener(this);
    }
 
    @MessageMapping("/chat")
    public void send(SimpMessageHeaderAccessor sha, @Payload Message chatMessage) throws Exception {
        Message message = new Message(chatMessage.getFromUser(), chatMessage.getMessage(), chatMessage.getUserDestination());
        messageRepository.save(message);
        MessageVO result = DozerConverter.parseObject(message, MessageVO.class);
        webSocket.convertAndSendToUser(chatMessage.getUserDestination().getEmail(), "/queue/messages", result);
    }

    @GetMapping(value = "/load-all-messages/{loggedUser}/{recipient}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MessageVO> loadAllMessages(@PathVariable(name = "recipient") Long recipient,
                                          @PathVariable(name = "loggedUser") Long loggedUserId) {
        User loggedUser = userRepository.findById(loggedUserId).get();

        List<Message> joinedList = new ArrayList<>();
        Stream.of(loggedUser.getMessagesAsRecipient().stream().filter(a -> a.getFromUser().getId() == recipient).collect(Collectors.toList()), 
                loggedUser.getMessagesAsSender().stream().filter(a -> a.getUserDestination().getId() == recipient).collect(Collectors.toList()))
                .collect(Collectors.toList())
                .forEach(joinedList::addAll);

        List<MessageVO> result = DozerConverter.parseListMessages(joinedList, MessageVO.class);
        result.sort(new MessageSorter().reversed());

        return result;
    }

    @Override
    public void notifyActiveUserChange() {
        Set<String> activeUsers = activeUserManager.getAll();
        webSocket.convertAndSend("/topic/active", activeUsers);
    }
}
