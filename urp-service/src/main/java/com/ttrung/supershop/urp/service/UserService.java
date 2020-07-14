package com.ttrung.supershop.urp.service;

import com.ttrung.supershop.urp.dto.Profile;
import com.ttrung.supershop.urp.exception.DuplicateEmailException;
import com.ttrung.supershop.urp.exception.SignInException;
import com.ttrung.supershop.urp.model.CustomUserDetails;
import com.ttrung.supershop.urp.model.Role;
import com.ttrung.supershop.urp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.ttrung.supershop.urp.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public String loginUser(Profile userProfile) {
        Optional<User> user = findByEmail(userProfile.getEmail());
        //Register user if not present
        if (!user.isPresent()) {
            user = Optional.ofNullable(registerUser(convertToUser(userProfile), Role.USER));
        }

        return user
                .map(CustomUserDetails::new)
                .map(userDetails -> new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()))
                .map(tokenProvider::generateToken)
                .orElseThrow(() ->
                        new SignInException("Unable to login for user " + userProfile.getEmail()));
    }

    public User registerUser(User user, Role role) {
        log.info("Registering user [{}]", user.getUsername());

        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("Email [{}] already exists.", user.getEmail());

            throw new DuplicateEmailException(String.format("Email [%s] already exists", user.getEmail()));
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(role));

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private User convertToUser(Profile userProfile) {
        return User.builder()
                .id(userProfile.getId())
                .email(userProfile.getEmail())
                .username(userProfile.getName())
                .password(generatePassword(8, 16))
                .build();
    }

    //TODO : Implement random pwd generation
    private String generatePassword(int minLength, int maxLength) {
        return "to-be-implemented-later";
    }
}
