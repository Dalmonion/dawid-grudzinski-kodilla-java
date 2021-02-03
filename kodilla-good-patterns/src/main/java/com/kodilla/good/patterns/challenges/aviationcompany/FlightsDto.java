package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.List;

public class FlightsDto {
    private final List<Flight> departureFlights;
    private final List<Flight> destinationFlights;
    private final List<Flight> flights;

    public FlightsDto(final List<Flight> departureFlights, final List<Flight> destinationFlights, final List<Flight> flights) {
        this.departureFlights = departureFlights;
        this.destinationFlights = destinationFlights;
        this.flights = flights;
    }

    public List<Flight> getDepartureFlights() {
        return departureFlights;
    }

    public List<Flight> getDestinationFlights() {
        return destinationFlights;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
