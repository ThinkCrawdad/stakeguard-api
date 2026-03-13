package com.stakeguard.api.services;

import com.stakeguard.api.dto.UserRegistrationDTO;
import com.stakeguard.api.dto.UserStatsDTO;
import com.stakeguard.api.models.Bet;
import com.stakeguard.api.models.User;

import java.util.List;

public interface UserService {
    User getUserById(Long userId);
    void recalculateTrustScore(Long userId);
    List<Bet> getUserBets(Long userId);
    User registerUser(UserRegistrationDTO userRegistrationDTO);
    UserStatsDTO getUserStats(Long userId);
}
