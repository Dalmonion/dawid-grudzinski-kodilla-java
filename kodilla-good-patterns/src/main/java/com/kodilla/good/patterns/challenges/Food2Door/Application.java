package com.kodilla.good.patterns.challenges.Food2Door;

public class Application {
    public static void main(String[] args) {

        CompaniesRetriever companiesRetriever = new CompaniesRetriever();
        CompaniesRepository companiesRepository = new CompaniesRepository(companiesRetriever.retrieve());
        OrderRequest orderRequest = new OrderRequest(new Item("Corn"), 99);

        OrderProcessor orderProcessor = new OrderProcessor(companiesRepository);
        orderProcessor.processOrder(orderRequest);
    }
}
