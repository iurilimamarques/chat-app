package com.iurimarques.apichatproject.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.fasterxml.jackson.core.sym.Name;
import com.iurimarques.apichatproject.data.vo.DestinationUserVO;
import com.iurimarques.apichatproject.data.vo.UserVO;
import com.iurimarques.apichatproject.services.UserService;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
@RequestMapping("api/api-chat/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserVO> selectUser(@RequestParam(name = "keyWord") String keyWord,
                                    @RequestParam(name = "loggedUser") Long loggedUser) {
        return userService.findUserByNameOrEmail(keyWord, loggedUser);
    }

    @GetMapping(value = "/active-chats/{loggedUser}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DestinationUserVO> selectActiveChats(@PathVariable("loggedUser") Long loggedUser) {
        List<DestinationUserVO> response = userService.selectActiveChats(loggedUser);
        return response;
    }
}