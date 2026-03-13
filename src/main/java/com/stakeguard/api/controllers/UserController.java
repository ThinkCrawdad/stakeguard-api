package com.stakeguard.api.controllers;

import com.stakeguard.api.dto.UserRegistrationDTO;
import com.stakeguard.api.models.Bet;
import com.stakeguard.api.models.User;
import com.stakeguard.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.registerUser(userRegistrationDTO);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{id}/history")
    public List<Bet> getUserHistory(@PathVariable Long id) {
        return userService.getUserBets(id);
    }

    @GetMapping("/{id}/stats")
    public com.stakeguard.api.dto.UserStatsDTO getUserStats(@PathVariable Long id) {
        return userService.getUserStats(id);
    }
}
