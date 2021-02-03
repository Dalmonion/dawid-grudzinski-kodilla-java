package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.List;
import java.util.Map;

public class FlightsDto {
    private final List<Flight> departureFlights;
    private final List<Flight> destinationFlights;
    private final Map<Flight, Flight> flightsStopover;
    private final SearchRequest searchRequest;

    public FlightsDto(final List<Flight> departureFlights, final List<Flight> destinationFlights, final Map<Flight, Flight> flightsStopover, final SearchRequest searchRequest) {
        this.departureFlights = departureFlights;
        this.destinationFlights = destinationFlights;
        this.flightsStopover = flightsStopover;
        this.searchRequest = searchRequest;
    }

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public List<Flight> getDestinationFlights() {
        return destinationFlights;
    }

    public Map<Flight, Flight> getFlightsStopover() {
        return flightsStopover;
    }

    public SearchRequest getSearchRequest() {
        return searchRequest;
    }
}
