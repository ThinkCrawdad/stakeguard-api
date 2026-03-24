package app.stakeguard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StakeGuardApplication {

    public static void main(String[] args) {
        SpringApplication.run(StakeGuardApplication.class, args);
        System.out.println("🚀 StakeGuard API corriendo en Java 25!");
    }
}