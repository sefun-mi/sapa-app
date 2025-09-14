package com.sapa.app.service.onboarding.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOnboardingRequest {
    private String username;

    private String email;
    private String phoneNumber;
    private String password;
}