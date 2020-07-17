package com.ttrung.supershop.product.config;

import com.ttrung.supershop.product.domain.ActivityType;
import com.ttrung.supershop.product.domain.UserActivity;
import com.ttrung.supershop.product.repository.UserActivityRepository;
import com.ttrung.supershop.product.util.AuthenticationExtractor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ActivityTrackingAspect {

    @Autowired
    private UserActivityRepository userActivityRepository;

    @Autowired
    private TaskExecutor taskExecutor;

    @Before("@annotation(com.ttrung.supershop.product.annotation.RecordProductSearch)")
    public void trackProductSearch(JoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            String filter = (String) args[0];
            String sort = (String) args[1];
            UserActivity userActivity = UserActivity.builder()
                    .id(UUID.randomUUID().toString())
                    .userId(AuthenticationExtractor.getAuthenticatedUser())
                    .actType(ActivityType.SEARCH)
                    .filter(filter)
                    .sortBy(sort)
                    .build();

            log.debug("Recording product search activity, filter [{}], sort [{}]", filter, sort);
            taskExecutor.execute(() -> userActivityRepository.save(userActivity));
        } catch (Exception e) {
            log.warn("Failed to record product search operation", e);
        }
    }
}