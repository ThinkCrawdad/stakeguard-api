package com.stakeguard.api.services;

import com.stakeguard.api.models.Fixture;
import com.stakeguard.api.repositories.FixtureRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FixtureServiceImpl implements FixtureService {

    private final FixtureRepository fixtureRepository;

    public FixtureServiceImpl(FixtureRepository fixtureRepository) {
        this.fixtureRepository = fixtureRepository;
    }

    @Override
    @Scheduled(cron = "0 0 1 * * ?")
    public void syncFixtures() {
        // Simulate fetching data from an external API
        // In a real application, you would make an HTTP request to the API
        // and parse the JSON response.
        // For now, we'll just create a few dummy fixtures.
        Fixture fixture1 = Fixture.builder()
                .externalApiId(1L)
                .homeTeam("Team A")
                .awayTeam("Team B")
                .league("Premier League")
                .startTime(LocalDateTime.now().plusHours(2))
                .status("SCHEDULED")
                .build();

        Fixture fixture2 = Fixture.builder()
                .externalApiId(2L)
                .homeTeam("Team C")
                .awayTeam("Team D")
                .league("La Liga")
                .startTime(LocalDateTime.now().plusHours(4))
                .status("SCHEDULED")
                .build();

        fixtureRepository.saveAll(List.of(fixture1, fixture2));
    }

    @Override
    public List<Fixture> getFixturesForToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        return fixtureRepository.findByStartTimeBetween(startOfDay, endOfDay);
    }
}
