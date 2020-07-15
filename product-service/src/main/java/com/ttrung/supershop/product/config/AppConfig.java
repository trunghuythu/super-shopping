/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends WebSecurityConfigurerAdapter {

    private static final String OAUTH_GRANT_TYPE = "client_credentials";
    private static final String OAUTH_SCOPE = "any";

    @Value("${TOKEN_CHECK_ENDPOINT}")
    private String tokenCheckEndpoint;

    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(String.format("%s?scope=%s&grant_type=%s", tokenCheckEndpoint, OAUTH_SCOPE, OAUTH_GRANT_TYPE));
        //TODO : Externalize client information to maintain portability
        tokenService.setClientId("supershop");
        tokenService.setClientSecret("supershop");
        return tokenService;
    }
}
