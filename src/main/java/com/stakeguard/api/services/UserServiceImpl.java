package com.stakeguard.api.services;

import com.stakeguard.api.models.Bet;
import com.stakeguard.api.models.User;
import com.stakeguard.api.repositories.BetRepository;
import com.stakeguard.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BetRepository betRepository;

    public UserServiceImpl(UserRepository userRepository, BetRepository betRepository) {
        this.userRepository = userRepository;
        this.betRepository = betRepository;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public void recalculateTrustScore(Long userId) {
        User user = getUserById(userId);
        List<Bet> bets = betRepository.findByUser(user);

        double score = 50.0;
        double totalStaked = 0;
        double netReturn = 0;

        for (Bet bet : bets) {
            totalStaked += bet.getStake();
            if ("WON".equals(bet.getStatus())) {
                score += 2.0;
                netReturn += bet.getStake() * (bet.getOdds() - 1);
            } else if ("LOST".equals(bet.getStatus())) {
                score -= 3.0;
                netReturn -= bet.getStake();
            }
        }

        double yield = totalStaked > 0 ? (netReturn / totalStaked) * 100 : 0;

        if (yield > 0) {
            score += 5.0;
        }

        score = Math.max(0, Math.min(100, score));
        user.setTrustScore(score);
        userRepository.save(user);
    }

    @Override
    public List<Bet> getUserBets(Long userId) {
        return betRepository.findAllByUserId(userId);
    }

    @Override
    public User registerUser(com.stakeguard.api.dto.UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setEmail(userRegistrationDTO.getEmail());
        // In a real application, you would hash the password before saving
        // user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setTrustScore(50.0);
        user.setPremium(false);

        return userRepository.save(user);
    }
}
