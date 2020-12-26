package com.iurimarques.apichatproject.controller;

import java.util.Objects;

import com.iurimarques.apichatproject.model.User;
import com.iurimarques.apichatproject.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/api-chat/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@RequestBody User user) {
        User checkUserExistence = userRepository.findByEmail(user.getEmail());
        if(!Objects.nonNull(checkUserExistence)) {
            return new ResponseEntity<>("Conta inexistente!", HttpStatus.BAD_REQUEST);
        }

        User userLogged = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(Objects.nonNull(userLogged)) {
            User userReturn = new User();
            userReturn.setEmail(userLogged.getEmail());
            return ResponseEntity
                .ok()
                .body(userReturn);
        }

        return new ResponseEntity<>("Senha incorreta!", HttpStatus.BAD_REQUEST);
    }
    

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody User user) {
        User userCreated = userRepository.save(user);
        if(!Objects.nonNull(userCreated)) {
            return new ResponseEntity<>("Não foi possível salvar!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userCreated);
    }
}