package com.stakeguard.api.controllers;

import com.stakeguard.api.models.User;
import com.stakeguard.api.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/waitlist")
public class WaitlistController {

    private final UserRepository userRepository;

    public WaitlistController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/join")
    public String joinWaitlist(@RequestBody String email) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(email); // Using email as a temporary username
        userRepository.save(user);
        return "¡Gracias! Ya estás en la lista de espera de StakeGuard.";
    }
}
