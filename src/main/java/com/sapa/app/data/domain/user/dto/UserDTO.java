package com.sapa.app.data.domain.user.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;

    private String email;
    private String phoneNumber;
    private String password;
    private Set<GrantedAuthority> authorities;
}