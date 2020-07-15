package com.ttrung.supershop.urp.service;

import com.ttrung.supershop.urp.dto.UserProfile;
import com.ttrung.supershop.urp.exception.DuplicateEmailException;
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

    public OAuth2AccessToken loginUser(UserProfile userProfile) {
        Optional<User> user = findByEmail(userProfile.getEmail());
        //Register user if not present
        if (!user.isPresent()) {
            user = Optional.of(registerUser(userProfile, Role.USER));
        }

        return user
                .map(CustomUserDetails::new)
                .map(this::generateAccessToken)
                .get();
    }

    public User registerUser(UserProfile userProfile, Role role) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(userProfile.getEmail())
                .username(userProfile.getName())
                .password(generatePassword(8, 16))
                .build();

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

    private String generatePassword(int minLength, int maxLength) {
        return "to-be-implemented-later";
    }

    private OAuth2AccessToken generateAccessToken(User user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Map<String, String> requestParameters = new HashMap<>();
        String clientId = "supershop";
        OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId,
                authorities, true, Collections.singleton("any"),
                null, null, null, null);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);
        return tokenServices.createAccessToken(auth);
    }
}
