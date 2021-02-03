package com.kodilla.good.patterns.challenges.aviationcompany;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserInformationService implements InformationService {
    @Override
    public void inform(FlightsDto flightsDto) {
        log.info("Flights from departure airport (" + flightsDto.getSearchRequest().getDeparture() + ")");
        for (Flight flight :flightsDto.getDepartureFlights()) {
            log.info("Flight number(" + flight.getFlightNumber() + ") from " + flight.getDeparture() + " to " + flight.getDestination());
        }

        log.info("Flights to destination airport (" + flightsDto.getSearchRequest().getDestination() + ")");
        for (Flight flight :flightsDto.getDestinationFlights()) {
            log.info("Flight number(" + flight.getFlightNumber() + ") from " + flight.getDeparture() + " to " + flight.getDestination());
        }

        log.info("Flights with stopovers");
        for (Map.Entry<Flight, Flight> entry : flightsDto.getFlightsStopover().entrySet()) {
            log.info("Flight number(" + entry.getKey().getFlightNumber() + ") from " + entry.getKey().getDeparture() +
                    " to " + entry.getKey().getDestination() + " and flight number (" + entry.getValue().getFlightNumber() + ")" +
                    " from " + entry.getValue().getDeparture() + " to " + entry.getValue().getDestination() + " - with stopover" +
                    " in " + entry.getValue().getDeparture());
        }

    }
}
