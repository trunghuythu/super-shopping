package com.ttrung.supershop.urp.service;

import com.ttrung.supershop.urp.dto.Profile;
import com.ttrung.supershop.urp.exception.DuplicateEmailException;
import com.ttrung.supershop.urp.exception.SignInException;
import com.ttrung.supershop.urp.model.CustomUserDetails;
import com.ttrung.supershop.urp.model.Role;
import com.ttrung.supershop.urp.model.User;
import com.ttrung.supershop.urp.repository.UserRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private DefaultTokenServices tokenServices;

    public UserService(DefaultTokenServices tokenServices,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.tokenServices = tokenServices;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public OAuth2AccessToken loginUser(Profile userProfile) {
        Optional<User> user = findByEmail(userProfile.getEmail());
        //Register user if not present
        if (!user.isPresent()) {
            user = Optional.ofNullable(registerUser(convertToUser(userProfile), Role.USER));
        }

        return user
                .map(CustomUserDetails::new)
                .map(u -> generateToken(u))
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
                .id(UUID.randomUUID().toString())
                .email(userProfile.getEmail())
                .username(userProfile.getName())
                .password(generatePassword(8, 16))
                .build();
    }

    //TODO : Implement random pwd generation
    private String generatePassword(int minLength, int maxLength) {
        return "to-be-implemented-later";
    }

    private OAuth2AccessToken generateToken(User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Map<String, String> requestParameters = new HashMap<>();
        String clientId = "supershop";
        boolean approved = true;
        Set<String> scope = new HashSet<>();
        scope.add("any");
        Set<String> resourceIds = new HashSet<>();
        Set<String> responseTypes = new HashSet<>();
        responseTypes.add("code");
        Map<String, Serializable> extensionProperties = new HashMap<>();

        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId,
                authorities, approved, scope,
                resourceIds, null, responseTypes, extensionProperties);


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);
        OAuth2AccessToken token = tokenServices.createAccessToken(auth);
        return token;
    }
}
