package com.ttrung.supershop.urp.controller;

import com.ttrung.supershop.urp.api.facebook.FacebookService;
import com.ttrung.supershop.urp.dto.UserProfile;
import com.ttrung.supershop.urp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrpController {

    private FacebookService facebookService;
    private UserService userService;

    @Autowired
    public UrpController(FacebookService facebookService, UserService userService) {
        this.facebookService = facebookService;
        this.userService = userService;
    }

    @GetMapping("/signin")
    public ResponseEntity<?> signIn() {
        UserProfile userProfile = facebookService.getProfile();

        OAuth2AccessToken token = userService.loginUser(userProfile);
        return ResponseEntity.ok(token);
    }
}
