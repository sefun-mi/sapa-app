package com.sapa.app.service.onboarding.service;

import com.sapa.app.data.actions.onboarding.dto.OnboardedUserDTO;
import com.sapa.app.data.actions.onboarding.service.UserOnboardingDataService;
import com.sapa.app.service.onboarding.request.UserOnboardingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOnboardingService {
    private final UserOnboardingDataService userOnboardingDataService;
    private final PasswordEncoder passwordEncoder;

    public OnboardedUserDTO onboard(UserOnboardingRequest userRequest){
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return userOnboardingDataService.onboardUser(userRequest);
    }
}