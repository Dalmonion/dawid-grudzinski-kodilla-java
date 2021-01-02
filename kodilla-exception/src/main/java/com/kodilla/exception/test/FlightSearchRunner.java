package com.kodilla.exception.test;

public class FlightSearchRunner {
    public static void main(String[] args) {
        FlightSearch flightSearch = new FlightSearch();

        Flight flight1 = new Flight("Dubai International Airport", "Atlanta International Airport");
        Flight flight2 = new Flight("Dubai International Airport", "Los Angeles International Airport");
        Flight flight3 = new Flight("Dubai International Airport", "Not Existing Airport");
        Flight flight4 = new Flight("", "");

        try {
            flightSearch.findFlight(flight1);
            flightSearch.findFlight(flight2);
            flightSearch.findFlight(flight3);
            flightSearch.findFlight(flight4);
        } catch (RouteNotFoundException e) {
            System.out.println("Airport destination not found");
        }



    }
}
