package com.kodilla.good.patterns.challenges.food2door;

public class Application {
    public static void main(String[] args) {

        CompaniesRetriever companiesRetriever = new CompaniesRetriever();
        CompaniesRepository companiesRepository = new CompaniesRepository(companiesRetriever.retrieve());

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.addItem(new Item("Corn", "ExtraFoodShop"), 99);
        orderRequest.addItem(new Item("Apple", "HealthyShop"), 99);

        OrderProcessor orderProcessor = new OrderProcessor(companiesRepository);
        orderProcessor.processOrder(orderRequest);
    }
}
