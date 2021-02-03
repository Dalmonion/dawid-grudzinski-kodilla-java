package com.kodilla.good.patterns.challenges.aviationcompany;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInformationService implements InformationService {
    @Override
    public void inform(FlightsDto flightsDto) {
        log.info("Flights from departure airport");
        for (Flight flight :flightsDto.getDepartureFlights()) {
            log.info(flight.getDeparture() + " : " + flight.getDestination());
        }

        log.info("Flights to destination airport");
        for (Flight flight :flightsDto.getDestinationFlights()) {
            log.info(flight.getDeparture() + " : " + flight.getDestination());
        }

    }
}
