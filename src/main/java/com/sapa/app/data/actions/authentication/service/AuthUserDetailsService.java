package com.sapa.app.data.actions.authentication.service;


import com.sapa.app.common.bean.SapaBeanUtil;
import com.sapa.app.data.actions.authentication.dto.AuthUserDetailsDTO;
import com.sapa.app.data.domain.user.model.User;
import com.sapa.app.data.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public AuthUserDetailsDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        return map(userRepository.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found")));
    }

    private AuthUserDetailsDTO map(User user){
        AuthUserDetailsDTO authUserDetailsDTO = new AuthUserDetailsDTO();
        SapaBeanUtil.copyPresentProperties(user, authUserDetailsDTO);
        return authUserDetailsDTO;
    }
}