package com.kodilla.exception.test;

import java.util.HashMap;
import java.util.Map;

public class FlightSearch {

    public void findFlight(Flight flight) throws RouteNotFoundException {
        Map<String, Boolean> availableAirportsMap = new HashMap<>();
        availableAirportsMap.put("Atlanta International Airport", true);
        availableAirportsMap.put("Dubai International Airport", false);
        availableAirportsMap.put("Tokyo International Airport", true);
        availableAirportsMap.put("Los Angeles International Airport", false);
        availableAirportsMap.put("O’Hare International Airport", true);


        if (availableAirportsMap.containsKey(flight.getArrivalAirport())) {
            boolean canFly = availableAirportsMap.entrySet().stream()
                    .filter(entry -> entry.getKey().equals(flight.getArrivalAirport()))
                    .anyMatch(entry -> entry.getValue().equals(true));
            if (canFly) {
                System.out.println(flight.getArrivalAirport() + " is available for flight");
            } else {
                System.out.println(flight.getArrivalAirport() + " is not available for flight right now");
            }
        } else {
            throw new RouteNotFoundException();
        }
    }
}
