package com.sapa.app.data.domain.user.service;

import com.sapa.app.common.bean.SapaBeanUtil;
import com.sapa.app.data.domain.user.dto.UserDTO;
import com.sapa.app.data.domain.user.model.User;
import com.sapa.app.data.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserDataAccessService {
    private final UserRepository userRepository;

    public UserDTO retrieveUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        ()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found")
                );
        return map(user);
    }

    private UserDTO map(User user){
        UserDTO userDTO = new UserDTO();
        SapaBeanUtil.copyPresentProperties(user, userDTO);
        return userDTO;
    }
}