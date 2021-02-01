package com.kodilla.good.patterns.challenges;

public class Application {
    public static void main(String[] args) {

        OrderRetriever orderRetriever = new OrderRetriever();
        OrderRequest orderRequest = orderRetriever.retrieve();

        OrderProcessor orderProcessor = new OrderProcessor(new PhoneInformationService(), new OrderItemService(), new ItemBuyRepository(), new Warehouse());
        orderProcessor.process(orderRequest);

    }
}
