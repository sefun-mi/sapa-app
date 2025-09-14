package com.sapa.app.data.domain.user.service;

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

    public UserDetails retrieveUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }
}