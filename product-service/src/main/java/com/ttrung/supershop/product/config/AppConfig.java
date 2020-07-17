/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.config;

import com.ttrung.supershop.product.util.CustomTokenService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig extends ResourceServerConfigurerAdapter {

    @Value("${TOKEN_CHECK_ENDPOINT}")
    private String tokenCheckEndpoint;

    @Bean
    @Primary
    public CustomTokenService tokenService() {
        CustomTokenService tokenService = new CustomTokenService(tokenCheckEndpoint);
        return tokenService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // Work-around to allow IPC without authentication
        http.authorizeRequests()
                .antMatchers("/v1/products/price-calculation").permitAll()
                .anyRequest().authenticated()
        ;
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("product-service-pool");
        executor.initialize();
        return executor;
    }
}
