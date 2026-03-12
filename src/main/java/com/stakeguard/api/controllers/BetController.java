package com.stakeguard.api.controllers;

import com.stakeguard.api.models.Bet;
import com.stakeguard.api.services.BetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping("/user/{userId}")
    public Bet placeBet(@PathVariable Long userId, @RequestBody Bet bet) {
        return betService.placeBet(userId, bet);
    }
}
