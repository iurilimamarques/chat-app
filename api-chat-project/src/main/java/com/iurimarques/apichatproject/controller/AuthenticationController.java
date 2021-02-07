package com.iurimarques.apichatproject.controller;

import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.iurimarques.apichatproject.converter.DozerConverter;
import com.iurimarques.apichatproject.data.vo.UserVO;
import com.iurimarques.apichatproject.model.User;
import com.iurimarques.apichatproject.repository.UserRepository;
import com.iurimarques.apichatproject.services.WebSocketConnectionService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/api-chat/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebSocketConnectionService websocketConnectionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity loginUser(@RequestBody User user,
                                    HttpServletRequest request) {
        User checkUserExistence = userRepository.findByEmail(user.getEmail());
        if(!Objects.nonNull(checkUserExistence)) {
            HashMap<String, String> response = new HashMap<>();
            response.put("response", "Conta inexistente!");
            return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(response);
        }

        User userLogged = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(Objects.nonNull(userLogged)) {
            UserVO userReturn = DozerConverter.parseObject(userLogged, UserVO.class);
            String remoteAddress = websocketConnectionService.connect(request, userReturn.getEmail());
            userReturn.setRemoteAddress(remoteAddress);
            return ResponseEntity
                .ok()
                .body(userReturn);
        }

        HashMap<String, String> response = new HashMap<>();
        response.put("response", "Senha incorreta!");
        return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
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