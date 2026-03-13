package com.stakeguard.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fixtures")
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long externalApiId;

    private String homeTeam;

    private String awayTeam;

    private String league;

    private LocalDateTime startTime;

    private String status; // SCHEDULED, FINISHED, CANCELLED
}
