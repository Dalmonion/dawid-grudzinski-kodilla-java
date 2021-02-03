package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.List;

public interface SearchService {
    List<Flight> findFlightsFrom(FlightsRepository database, Flight departure);
    List<Flight> findFlightsTo(FlightsRepository database, Flight destination);
    List<Flight> findFlights(FlightsRepository database, Flight departure, Flight destination);
}
