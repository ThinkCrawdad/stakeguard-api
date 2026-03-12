package com.stakeguard.api.services;

import com.stakeguard.api.models.Bet;
import com.stakeguard.api.repositories.BetRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final BetRepository betRepository;
    private final UserService userService;

    public AuditServiceImpl(BetRepository betRepository, UserService userService) {
        this.betRepository = betRepository;
        this.userService = userService;
    }

    @Override
    public void resolveBet(Long betId, String finalStatus) {
        Bet bet = betRepository.findById(betId)
                .orElseThrow(() -> new IllegalArgumentException("Bet not found"));

        if (!"PENDING".equals(bet.getStatus())) {
            throw new IllegalStateException("Bet has already been resolved");
        }

        if (!"WON".equals(finalStatus) && !"LOST".equals(finalStatus)) {
            throw new IllegalArgumentException("Invalid final status");
        }

        bet.setStatus(finalStatus);
        betRepository.save(bet);

        userService.recalculateTrustScore(bet.getUser().getId());
    }
}
