package com.stakeguard.api.services;

import com.stakeguard.api.models.Bet;

public interface BetService {
    Bet placeBet(Long userId, Bet bet);
}
