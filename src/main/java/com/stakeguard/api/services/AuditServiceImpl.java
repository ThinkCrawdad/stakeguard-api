package com.stakeguard.api.services;

import com.stakeguard.api.exceptions.IllegalActionException;
import com.stakeguard.api.models.Bet;
import com.stakeguard.api.models.Selection;
import com.stakeguard.api.repositories.BetRepository;
import com.stakeguard.api.repositories.SelectionRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {

    private final BetRepository betRepository;
    private final SelectionRepository selectionRepository;
    private final UserService userService;

    public AuditServiceImpl(BetRepository betRepository, SelectionRepository selectionRepository, UserService userService) {
        this.betRepository = betRepository;
        this.selectionRepository = selectionRepository;
        this.userService = userService;
    }

    @Override
    public void resolveSelection(Long selectionId, String finalStatus) {
        Selection selection = selectionRepository.findById(selectionId)
                .orElseThrow(() -> new IllegalArgumentException("Selection not found"));

        Bet bet = selection.getBet();
        if (!"PENDING".equals(bet.getStatus())) {
            throw new IllegalActionException("La apuesta ya ha sido resuelta y no puede ser modificada.");
        }

        selection.setStatus(finalStatus);
        selectionRepository.save(selection);

        if ("LOST".equals(finalStatus)) {
            bet.setStatus("LOST");
            betRepository.save(bet);
            userService.recalculateTrustScore(bet.getUser().getId());
        } else if ("WON".equals(finalStatus)) {
            boolean allSelectionsWon = bet.getSelections().stream()
                    .allMatch(s -> "WON".equals(s.getStatus()));
            if (allSelectionsWon) {
                bet.setStatus("WON");
                betRepository.save(bet);
                userService.recalculateTrustScore(bet.getUser().getId());
            }
        }
    }
}
