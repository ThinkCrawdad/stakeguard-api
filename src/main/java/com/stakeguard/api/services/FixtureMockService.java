package com.stakeguard.api.services;

import com.stakeguard.api.models.Fixture;
import com.stakeguard.api.repositories.FixtureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FixtureMockService {

    private final FixtureRepository fixtureRepository;

    public FixtureMockService(FixtureRepository fixtureRepository) {
        this.fixtureRepository = fixtureRepository;
    }

    public void createMockFixtures() {
        if (fixtureRepository.count() > 0) {
            return; // Fixtures already exist
        }

        List<Fixture> fixtures = List.of(
                Fixture.builder().externalApiId(1001L).homeTeam("América").awayTeam("Chivas").league("Liga MX").startTime(LocalDateTime.now().plusHours(1)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1002L).homeTeam("Cruz Azul").awayTeam("Pumas").league("Liga MX").startTime(LocalDateTime.now().plusHours(3)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1003L).homeTeam("Tigres").awayTeam("Monterrey").league("Liga MX").startTime(LocalDateTime.now().plusDays(1).withHour(19)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1004L).homeTeam("Atlas").awayTeam("León").league("Liga MX").startTime(LocalDateTime.now().plusDays(1).withHour(21)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1005L).homeTeam("Toluca").awayTeam("Pachuca").league("Liga MX").startTime(LocalDateTime.now().plusHours(2)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1006L).homeTeam("Santos").awayTeam("Necaxa").league("Liga MX").startTime(LocalDateTime.now().plusDays(1).withHour(17)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1007L).homeTeam("Querétaro").awayTeam("San Luis").league("Liga MX").startTime(LocalDateTime.now().plusHours(5)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1008L).homeTeam("Juárez").awayTeam("Tijuana").league("Liga MX").startTime(LocalDateTime.now().plusDays(1).withHour(19)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1009L).homeTeam("Mazatlán").awayTeam("Puebla").league("Liga MX").startTime(LocalDateTime.now().plusHours(6)).status("SCHEDULED").build(),
                Fixture.builder().externalApiId(1010L).homeTeam("León").awayTeam("América").league("Liga MX").startTime(LocalDateTime.now().plusDays(2)).status("SCHEDULED").build()
        );

        fixtureRepository.saveAll(fixtures);
    }
}
