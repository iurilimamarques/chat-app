package com.iurimarques.apichatproject.services;

import java.time.LocalDateTime;
import java.util.List;

import com.iurimarques.apichatproject.converter.DozerConverter;
import com.iurimarques.apichatproject.data.vo.DestinationUserVO;
import com.iurimarques.apichatproject.data.vo.UserVO;
import com.iurimarques.apichatproject.model.User;
import com.iurimarques.apichatproject.repository.UserRepository;
import com.iurimarques.apichatproject.utils.LastInteractionSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserVO> findUserByNameOrEmail(String keyWord, Long loggedUser) {
        return DozerConverter.parseListObjects(userRepository.findByEmailOrName(keyWord, loggedUser), UserVO.class);
    }

    public List<DestinationUserVO> selectActiveChats(Long loggedUser) {
        List<User> listUsers = userRepository.selectActiveChats(loggedUser);
        List<DestinationUserVO> listConvertedUsers = DozerConverter.parseListObjects(listUsers, DestinationUserVO.class);

        listConvertedUsers.stream().forEach(a -> {
            LocalDateTime lastInteraction = userRepository.getLastInteractionWith(loggedUser, a.getId());
            a.setLastInteractedWith(lastInteraction);
        });

        listConvertedUsers.sort(new LastInteractionSorter());
        return listConvertedUsers;
    }
}
