package com.kodilla.good.patterns.challenges.aviationcompany;

public class Flight {
    private final String departure;
    private final String destination;
    private final int flightNumber;

    public Flight(final String departure, final String destination, final int flightNumber) {
        this.departure = departure;
        this.destination = destination;
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return this.hashCode() == flight.hashCode();
    }

    @Override
    public int hashCode() {
        int result = departure != null ? departure.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + flightNumber;
        return result;
    }
}
