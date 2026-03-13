package com.stakeguard.api.repositories;

import com.stakeguard.api.models.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
    List<Fixture> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
