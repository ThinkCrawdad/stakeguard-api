package com.stakeguard.api.repositories;

import com.stakeguard.api.models.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
