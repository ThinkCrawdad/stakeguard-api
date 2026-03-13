package com.stakeguard.api.services;

import com.stakeguard.api.models.Fixture;

import java.util.List;

public interface FixtureService {
    void syncFixtures();
    List<Fixture> getFixturesForToday();
}
