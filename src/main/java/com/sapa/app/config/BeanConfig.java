package com.sapa.app.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.web.client.RestTemplate;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Configuration
public class BeanConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.secret}")
    private String secret;

    private final SecretKeySpec secretKeySpec = new SecretKeySpec(toFixed256Bytes(secret),"HmacSHA256");

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(secretKeySpec));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static byte[] toFixed256Bytes(String input) { //idempotent
        byte[] originalBytes = input.getBytes(StandardCharsets.UTF_8);

        if (originalBytes.length > 256) {
            // Truncate if longer than 256 bytes
            return Arrays.copyOf(originalBytes, 256);
        } else if (originalBytes.length < 256) {
            // Pad with zeros if shorter than 256 bytes
            byte[] padded = new byte[256];
            System.arraycopy(originalBytes, 0, padded, 0, originalBytes.length);
            return padded;
        } else {
            // Exactly 256 bytes
            return originalBytes;
        }
    }
}