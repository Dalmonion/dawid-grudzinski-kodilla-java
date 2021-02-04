package com.kodilla.good.patterns.challenges.aviationcompany;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class FlightSearchProcessor implements SearchService {

    private final FlightsRepository database;

    public FlightSearchProcessor(final FlightsRepository database) {
        this.database = database;
    }

    @Override
    public List<Flight> findFlightsFrom(String departure) {
        return database.getFlightDatabase().stream()
                .filter(e -> departure.equals(e.getDeparture()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> findFlightsTo(String destination) {
        return database.getFlightDatabase().stream()
                .filter(e -> destination.equals(e.getDestination()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Flight, Flight> findFlightsStopover(String departure, String destination) {
        List<Flight> flightsFromDeparture = findFlightsFrom(departure);
        List<Flight> flightsToDestination = findFlightsTo(destination);
        Map<Flight, Flight> flightsWithStopovers = new HashMap<>();

        for (Flight flightFrom : flightsFromDeparture) {
            for (Flight flightTo : flightsToDestination) {
                if (flightFrom.getDestination().equals(flightTo.getDeparture())) {
                    flightsWithStopovers.put(flightFrom, flightTo);
                }
            }
        }
        return new HashMap<>(flightsWithStopovers);
    }
}
