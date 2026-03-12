package com.stakeguard.api.repositories;

import com.stakeguard.api.models.Bet;
import com.stakeguard.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByUser(User user);
}
