package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.List;
import java.util.Map;

public class FlightSearchService {

    private final InformationService informationService;
    private final SearchService searchService;

    public FlightSearchService(final UserInformationService informationService, final FlightSearchProcessor searchService) {
        this.informationService = informationService;
        this.searchService = searchService;
    }

    public void processSearch(final SearchRequest searchRequest) {
        List<Flight> flightsFrom = searchService.findFlightsFrom(searchRequest.getDeparture());
        List<Flight> flightsTo = searchService.findFlightsTo(searchRequest.getDestination());
        Map<Flight, Flight> flightsStopover = searchService.findFlightsStopover(searchRequest.getDeparture(), searchRequest.getDestination());

        FlightsDto results = new FlightsDto(flightsFrom, flightsTo, flightsStopover, searchRequest);
        informationService.inform(results);
    }
}
