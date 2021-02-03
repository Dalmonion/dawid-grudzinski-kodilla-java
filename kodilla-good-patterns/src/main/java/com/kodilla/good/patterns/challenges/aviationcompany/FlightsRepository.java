package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.HashMap;
import java.util.Map;

public class FlightsRepository {

    private final Map<Integer, Flight> flightDatabase;

    public FlightsRepository(final Map<Integer, Flight> flightDatabase) {
        this.flightDatabase = flightDatabase;
    }

    public Map<Integer, Flight> getFlightDatabase() {
        return new HashMap<>(flightDatabase);
    }
}
