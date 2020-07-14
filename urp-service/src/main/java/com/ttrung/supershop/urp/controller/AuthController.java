package com.ttrung.supershop.urp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttrung.supershop.urp.api.facebook.FacebookService;
import com.ttrung.supershop.urp.dto.Profile;
import com.ttrung.supershop.urp.dto.JwtAuthenticationResponse;
import com.ttrung.supershop.urp.service.UserService;

@RestController
public class AuthController {

    private FacebookService facebookService;
    private UserService userService;

    @Autowired
    public AuthController(FacebookService facebookService, UserService userService) {
        this.facebookService = facebookService;
        this.userService = userService;
    }

    @GetMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn() {
        Profile userProfile = facebookService.getProfile();

        String token = userService.loginUser(userProfile);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @GetMapping("/api/products")
    public ResponseEntity<?> routeRequests() {
        return ResponseEntity.ok("Hey");
    }
}
