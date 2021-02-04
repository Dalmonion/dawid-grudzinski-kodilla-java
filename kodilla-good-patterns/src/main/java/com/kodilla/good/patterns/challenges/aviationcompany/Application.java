package com.kodilla.good.patterns.challenges.aviationcompany;

public class Application {
    public static void main(String[] args) {
        FlightsRepositoryRetriever flightsRepositoryRetriever = new FlightsRepositoryRetriever();
        FlightsRepository flightsRepository = new FlightsRepository(flightsRepositoryRetriever.retrieve());

        FlightSearchService processor = new FlightSearchService(new UserInformationService(), new FlightSearchProcessor(flightsRepository));
        SearchRequest request = new SearchRequest(Location.WARSZAWA.toString(), Location.RZESZOW.toString());
        processor.processSearch(request);
    }


}
