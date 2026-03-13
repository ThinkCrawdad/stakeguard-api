package com.stakeguard.api.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double stake;

    private String status; // PENDING, WON, LOST

    private LocalDateTime timestampCreated = LocalDateTime.now();

    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL)
    private List<Selection> selections;

    private Double totalOdds;

    @ManyToOne
    private User user;
}
