package com.sapa.app.service.authentication.service;


import com.sapa.app.data.actions.authentication.dto.AuthUserDetailsDTO;
import com.sapa.app.data.actions.authentication.service.AuthUserDetailsService;
import com.sapa.app.service.authentication.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthUserDetailsService authUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(String username, String password){
        AuthUserDetailsDTO userDetails = authUserDetailsService.loadUserByUsername(username);
        boolean correctPassword = passwordEncoder.matches(password,userDetails.getPassword());
        if (! correctPassword){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Incorrect Username or Password");
        }

        String token = jwtService.generateJWT(userDetails.getUsername(), userDetails.getPermissions());
        return AuthenticationResponse.builder().token(token).build();
    }


}