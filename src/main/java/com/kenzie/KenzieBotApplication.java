package com.kenzie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = {"com.kenzie"})
@EnableJpaRepositories(basePackages = {"com.kenzie"})
@SpringBootApplication
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class KenzieBotApplication extends ListenerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(KenzieBotApplication.class, args);
    }
}