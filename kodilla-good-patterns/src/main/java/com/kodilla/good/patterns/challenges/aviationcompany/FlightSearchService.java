package com.kodilla.good.patterns.challenges.aviationcompany;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class FlightSearchService implements SearchService {
    @Override
    public List<Flight> findFlightsFrom(FlightsRepository database, String departure) {
        List<Flight> searchResults;

        searchResults = database.getFlightDatabase().stream()
                .filter(e -> e.getDeparture().equals(departure))
                .collect(Collectors.toList());

        return searchResults;


    }

    @Override
    public List<Flight> findFlightsTo(FlightsRepository database, String destination) {
        List<Flight> searchResults;

        searchResults = database.getFlightDatabase().stream()
                .filter(e -> e.getDestination().equals(destination))
                .collect(Collectors.toList());
        return searchResults;
    }

    @Override
    public Map<Flight, Flight> findFlightsStopover(FlightsRepository database, String departure, String destination) {
        List<Flight> flightsFromDeparture = findFlightsFrom(database, departure);
        List<Flight> flightsToDestination = findFlightsTo(database, destination);

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
