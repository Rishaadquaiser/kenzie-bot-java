package com.kenzie.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SchedulingConfig {

    @Value("${SCHEDULING_ENABLED:false}")
    private boolean schedulingEnabled;

    @Bean
    public boolean kenzieBotScheduling() {
        return schedulingEnabled;
    }
}
