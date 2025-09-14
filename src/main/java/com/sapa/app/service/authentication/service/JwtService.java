package com.sapa.app.service.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String generateJWT(String username, Set<GrantedAuthority> authorities){

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("sapa")
                .issuedAt(Instant.now())
                .expiresAt(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant())
                .subject(username)
                .claim("permissions", authorities)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String extractUsername(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public String extractClaimFromToken(String claim, String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getClaimAsString(claim);
    }

}