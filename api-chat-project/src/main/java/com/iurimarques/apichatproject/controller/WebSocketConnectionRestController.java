package com.iurimarques.apichatproject.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.iurimarques.apichatproject.services.WebSocketConnectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/api-chat/websocket-connection")
public class WebSocketConnectionRestController {

    @Autowired
    private WebSocketConnectionService websocketConnectionService;
    
    @PostMapping("/user-connect")
    public String userConnect(@ModelAttribute("username") String userName,
                              HttpServletRequest request) {
        return websocketConnectionService.connect(request, userName);
    }
    
    @PostMapping("/user-disconnect")
    public String userDisconnect(@ModelAttribute("username") String userName) {
        return websocketConnectionService.remove(userName);
    }
    
    @GetMapping(value = "/active-users-except/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> getActiveUsersExceptCurrentUser(@PathVariable("userName") String userName) {
        return websocketConnectionService.getActiveUsersExceptCurrentUser(userName);
    }
}
