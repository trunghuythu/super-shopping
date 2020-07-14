package com.ttrung.supershop.urp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfig {
    private String header;
    private String prefix;
    private int expiration;
    private String secret;
}
