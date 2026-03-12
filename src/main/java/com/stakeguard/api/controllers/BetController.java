package com.stakeguard.api.controllers;

import com.stakeguard.api.models.Bet;
import com.stakeguard.api.services.AuditService;
import com.stakeguard.api.services.BetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bets")
public class BetController {

    private final BetService betService;
    private final AuditService auditService;

    public BetController(BetService betService, AuditService auditService) {
        this.betService = betService;
        this.auditService = auditService;
    }

    @PostMapping("/place")
    public Bet placeBet(@RequestParam Long userId, @RequestBody Bet bet) {
        return betService.placeBet(userId, bet);
    }

    @PostMapping("/resolve/{betId}")
    public void resolveBet(@PathVariable Long betId, @RequestParam String status) {
        auditService.resolveBet(betId, status);
    }
}
