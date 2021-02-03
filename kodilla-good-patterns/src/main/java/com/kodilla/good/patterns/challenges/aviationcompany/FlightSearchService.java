package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightSearchService implements SearchService {
    @Override
    public List<Flight> findFlightsFrom(FlightsRepository database, Flight departure) {
        List<Flight> searchResults;

        searchResults = database.getFlightDatabase().entrySet().stream()
                .filter(e -> e.getValue().getDeparture().equals(departure.getDeparture()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return searchResults;
    }

    @Override
    public List<Flight> findFlightsTo(FlightsRepository database, Flight destination) {
        List<Flight> searchResults;

        searchResults = database.getFlightDatabase().entrySet().stream()
                .filter(e -> e.getValue().getDestination().equals(destination.getDestination()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        return searchResults;
    }

    @Override
    public List<Flight> findFlights(FlightsRepository database, Flight departure, Flight destination) {
        return null;
    }
}
