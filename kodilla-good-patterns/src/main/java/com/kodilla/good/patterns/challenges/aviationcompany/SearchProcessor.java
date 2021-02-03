package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchProcessor {

    private final InformationService informationService;
    private final SearchService searchService;
    private final FlightsRepository flightsRepository;


    public SearchProcessor(final UserInformationService informationService, final FlightSearchService searchService,
                           final FlightsRepository flightsRepository) {
        this.informationService = informationService;
        this.searchService = searchService;
        this.flightsRepository = flightsRepository;
    }

    public void processSearch(final SearchRequest searchRequest) {
        List<Flight> flightsFrom = searchService.findFlightsFrom(flightsRepository, searchRequest.getDeparture());
        List<Flight> flightsTo = searchService.findFlightsTo(flightsRepository, searchRequest.getDestination());
        Map<Flight, Flight> flightsStopover = searchService.findFlightsStopover(flightsRepository, searchRequest.getDeparture(), searchRequest.getDestination());

        FlightsDto results = new FlightsDto(flightsFrom, flightsTo, flightsStopover, searchRequest);
        informationService.inform(results);
    }
}
