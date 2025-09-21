package com.sapa.app.service.onboarding.service;

import com.sapa.app.common.bean.SapaBeanUtil;
import com.sapa.app.data.actions.onboarding.dto.OnboardedUserDTO;
import com.sapa.app.data.actions.onboarding.service.UserOnboardingDataService;
import com.sapa.app.service.onboarding.request.OnboardingRequest;
import com.sapa.app.service.onboarding.response.OnboardingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OnboardingService {
    private final UserOnboardingDataService userOnboardingDataService;
    private final PasswordEncoder passwordEncoder;

    public OnboardedUserDTO onboard(OnboardingRequest onboardingRequest){
        onboardingRequest.setPassword(passwordEncoder.encode(onboardingRequest.getPassword()));
        return userOnboardingDataService.onboardUser(onboardingRequest);
    }

    private OnboardingResponse map(OnboardedUserDTO onboardedUserDTO){
        OnboardingResponse onboardingResponse = new OnboardingResponse();
        SapaBeanUtil.copyPresentProperties(onboardedUserDTO, onboardingResponse);
        return onboardingResponse;
    }
}