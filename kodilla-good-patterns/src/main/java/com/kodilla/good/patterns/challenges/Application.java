package com.kodilla.good.patterns.challenges;

public class Application {
    public static void main(String[] args) {

        OrderRetriever orderRetriever = new OrderRetriever();
        OrderRequest orderRequest = orderRetriever.retrieve();
    }
}
