package com.stakeguard.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matchId;

    private Double stake;

    private Double odds;

    private String status; // PENDING, WON, LOST

    private LocalDateTime timestampCreated = LocalDateTime.now();

    private LocalDateTime eventStartTime;

    @ManyToOne
    private User user;
}
