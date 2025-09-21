package com.sapa.app.service.onboarding.controller;

import com.sapa.app.service.onboarding.request.OnboardingRequest;
import com.sapa.app.service.onboarding.service.OnboardingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/onboarding")
@Tag(name = "Onboarding")
public class OnboardingController {
    private final OnboardingService onboardingService;

    @PostMapping()
    ResponseEntity<Object> login(@RequestBody OnboardingRequest onboardingRequest){
        return ResponseEntity.ok().body(
                onboardingService.onboard(onboardingRequest)
        );
    }
}