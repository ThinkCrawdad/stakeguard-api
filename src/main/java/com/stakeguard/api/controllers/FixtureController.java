package com.stakeguard.api.controllers;

import com.stakeguard.api.models.Fixture;
import com.stakeguard.api.services.FixtureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fixtures")
public class FixtureController {

    private final FixtureService fixtureService;

    public FixtureController(FixtureService fixtureService) {
        this.fixtureService = fixtureService;
    }

    @GetMapping("/today")
    public List<Fixture> getFixturesForToday() {
        return fixtureService.getFixturesForToday();
    }
}
