package com.stakeguard.api.controllers;

import com.stakeguard.api.dto.PlaceBetDTO;
import com.stakeguard.api.models.Bet;
import com.stakeguard.api.services.BetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parlays")
public class ParlayController {

    private final BetService betService;

    public ParlayController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping("/place")
    public Bet placeParlay(@RequestBody PlaceBetDTO placeBetDTO) {
        return betService.placeBet(placeBetDTO);
    }
}
