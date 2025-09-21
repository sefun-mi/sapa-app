package com.sapa.app.service.authentication.controller;

import com.sapa.app.service.authentication.request.AuthenticationRequest;
import com.sapa.app.service.authentication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok().body(
                authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
    }
}