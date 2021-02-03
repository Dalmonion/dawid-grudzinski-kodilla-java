package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Flight> findFlightsFrom(FlightsRepository database, String departure);
    List<Flight> findFlightsTo(FlightsRepository database, String destination);
    Map<Flight, Flight> findFlightsStopover(FlightsRepository database, String departure, String destination);
}
