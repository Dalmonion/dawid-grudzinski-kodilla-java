package com.kodilla.good.patterns.challenges.aviationcompany;

public class SearchRequest {
    private final String departure;
    private final String destination;

    public SearchRequest(final String departure, final String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }
}
