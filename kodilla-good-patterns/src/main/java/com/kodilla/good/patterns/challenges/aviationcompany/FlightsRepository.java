package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsRepository {

    private final List<Flight> flightDatabase;

    public FlightsRepository(final List <Flight> flightDatabase) {
        this.flightDatabase = flightDatabase;
    }

    public List<Flight> getFlightDatabase() {
        return new ArrayList<>(flightDatabase);
    }
}
