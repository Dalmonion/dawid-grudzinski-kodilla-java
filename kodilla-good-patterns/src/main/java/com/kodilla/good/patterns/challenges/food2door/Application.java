package com.kodilla.good.patterns.challenges.food2door;

public class Application {
    public static void main(String[] args) {

        CompaniesRetriever companiesRetriever = new CompaniesRetriever();
        CompaniesRepository companiesRepository = new CompaniesRepository(companiesRetriever.retrieve());
        OrderRequest orderRequest = new OrderRequest(new Item("Corn"), 99);
        OrderRequest orderRequest2 = new OrderRequest(new Item("Apple"), 99);

        OrderProcessor orderProcessor = new OrderProcessor(companiesRepository);
        orderProcessor.processOrder(orderRequest);
        orderProcessor.processOrder(orderRequest2);
    }
}
