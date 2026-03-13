package com.stakeguard.api.services;

import com.stakeguard.api.dto.PlaceBetDTO;
import com.stakeguard.api.models.Bet;

public interface BetService {
    Bet placeBet(PlaceBetDTO placeBetDTO);
}
