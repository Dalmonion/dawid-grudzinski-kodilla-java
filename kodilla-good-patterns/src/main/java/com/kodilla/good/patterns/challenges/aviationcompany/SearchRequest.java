package com.kodilla.good.patterns.challenges.aviationcompany;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchRequest {
    private final String departure;
    private final String destination;

    public SearchRequest(final String departure, final String destination) {
        this.departure = departure;
        this.destination = destination;
        log.info("Creating request. Departure airport: " + departure + ", Destination airport: " +destination);
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }
}
