package com.stakeguard.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStatsDTO {
    private long totalPicks;
    private double winRate;
    private double yield;
    private double profit;
}
