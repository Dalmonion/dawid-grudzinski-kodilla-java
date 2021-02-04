package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Flight> findFlightsFrom(String departure);
    List<Flight> findFlightsTo(String destination);
    Map<Flight, Flight> findFlightsStopover(String departure, String destination);
}
