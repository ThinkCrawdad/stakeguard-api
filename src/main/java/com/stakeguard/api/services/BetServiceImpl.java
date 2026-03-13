package com.stakeguard.api.services;

import com.stakeguard.api.exceptions.IllegalActionException;
import com.stakeguard.api.models.Bet;
import com.stakeguard.api.models.User;
import com.stakeguard.api.repositories.BetRepository;
import com.stakeguard.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;
    private final UserRepository userRepository;

    public BetServiceImpl(BetRepository betRepository, UserRepository userRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bet placeBet(Long userId, Bet bet) {
        if (bet.getEventStartTime().isBefore(LocalDateTime.now())) {
            throw new IllegalActionException("No se pueden registrar apuestas de eventos que ya han comenzado");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        bet.setUser(user);
        bet.setStatus("PENDING");
        bet.setTimestampCreated(LocalDateTime.now());

        return betRepository.save(bet);
    }
}
