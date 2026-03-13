package com.stakeguard.api;

import com.stakeguard.api.services.FixtureMockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StakeGuardApplication {

    public static void main(String[] args) {
        SpringApplication.run(StakeGuardApplication.class, args);
    }

    @Bean
    CommandLineRunner run(FixtureMockService fixtureMockService) {
        return args -> {
            fixtureMockService.createMockFixtures();
        };
    }
}
