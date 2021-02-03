package com.kodilla.good.patterns.challenges.aviationcompany;

public class SearchProcessor {

    private final InformationService informationService;
    private final SearchService searchService;


    public SearchProcessor(final InformationService informationService, final SearchService searchService) {
        this.informationService = informationService;
        this.searchService = searchService;
    }

    public void processSearch(final SearchRequest searchRequest) {

    }
}
