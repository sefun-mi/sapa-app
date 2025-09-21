package com.sapa.app.data.actions.authentication.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthUserDetailsDTO implements UserDetails {
    private String username;
    private String password;
    private Set<String> permissions;

    @Override
    public Set<GrantedAuthority> getAuthorities(){
        return permissions.stream().map(string->new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return string;
            }
        }
        ).collect(Collectors.toSet());
    }
}