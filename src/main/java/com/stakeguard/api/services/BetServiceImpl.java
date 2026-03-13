package com.stakeguard.api.services;

import com.stakeguard.api.exceptions.IllegalActionException;
import com.stakeguard.api.models.Bet;
import com.stakeguard.api.models.Selection;
import com.stakeguard.api.models.User;
import com.stakeguard.api.repositories.BetRepository;
import com.stakeguard.api.repositories.SelectionRepository;
import com.stakeguard.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final SelectionRepository selectionRepository;

    public BetServiceImpl(BetRepository betRepository, UserRepository userRepository, SelectionRepository selectionRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.selectionRepository = selectionRepository;
    }

    @Override
    public Bet placeBet(com.stakeguard.api.dto.PlaceBetDTO placeBetDTO) {
        Bet bet = new Bet();
        bet.setSelections(placeBetDTO.getSelections());
        bet.setStake(placeBetDTO.getStake());

        for (Selection selection : bet.getSelections()) {
            if (selection.getEventStartTime().isBefore(LocalDateTime.now())) {
                throw new IllegalActionException("No se pueden registrar apuestas de eventos que ya han comenzado");
            }
        }

        User user = userRepository.findById(placeBetDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        bet.setUser(user);
        bet.setStatus("PENDING");
        bet.setTimestampCreated(LocalDateTime.now());

        double totalOdds = 1.0;
        for (Selection selection : bet.getSelections()) {
            totalOdds *= selection.getOdds();
            selection.setBet(bet);
        }
        bet.setTotalOdds(totalOdds);

        return betRepository.save(bet);
    }
}
