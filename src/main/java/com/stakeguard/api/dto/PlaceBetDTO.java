package com.stakeguard.api.dto;

import com.stakeguard.api.models.Selection;
import lombok.Data;

import java.util.List;

@Data
public class PlaceBetDTO {
    private Long userId;
    private List<Selection> selections;
    private Double stake;
}
