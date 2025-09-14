package com.sapa.app.data.actions.onboarding.service;

import com.sapa.app.common.bean.SapaBeanUtil;
import com.sapa.app.data.actions.onboarding.dto.OnboardedUserDTO;
import com.sapa.app.data.domain.user.model.User;
import com.sapa.app.data.domain.user.repository.UserRepository;
import com.sapa.app.service.onboarding.request.UserOnboardingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOnboardingDataService {
    private final UserRepository userRepository;

    public OnboardedUserDTO onboardUser(UserOnboardingRequest userRequest){
        User user = new User();
        SapaBeanUtil.copyPresentProperties(userRequest, user);
        userRepository.save(user);

        return map(user);
    }
    private OnboardedUserDTO map(User user){
        OnboardedUserDTO onboardedUserDTO = new OnboardedUserDTO();
        SapaBeanUtil.copyPresentProperties(user, onboardedUserDTO);
        return onboardedUserDTO;
    }
}