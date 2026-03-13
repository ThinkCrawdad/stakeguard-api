package com.stakeguard.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StakeGuardApplication {

    public static void main(String[] args) {
        SpringApplication.run(StakeGuardApplication.class, args);
    }

}
