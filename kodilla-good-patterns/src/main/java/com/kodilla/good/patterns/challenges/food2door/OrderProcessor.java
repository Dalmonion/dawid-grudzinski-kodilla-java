package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderProcessor {

    private final CompaniesRepository database;
    private final InformationExtractor informationExtractor;
    private final ItemChecker checker;


    public OrderProcessor(final CompaniesRepository database) {
        this.database = database;
        this.informationExtractor = new InformationExtractor();
        this.checker = new ItemChecker();
    }

    public OrderDto processOrder(OrderRequest orderRequest) {

        CompanyInfo basicInformation = informationExtractor.extractData(database, orderRequest);


        boolean isOnList = checker.isOnList(database, orderRequest.getItem());
        if (isOnList) {
            if (basicInformation.getProductAmount() > 0 && orderRequest.getAmount() <= basicInformation.getProductAmount()) {
                log.info("Proceeding with order");
                checker.processExecutor(database, orderRequest.getItem(), orderRequest.getAmount());
                return new OrderDto(basicInformation.getCompanyName(), basicInformation.getProductName(), orderRequest.getAmount(), true);

            } else {
                log.info("Canceling order caused by lack of product in stock");
                return new OrderDto(basicInformation.getCompanyName(), basicInformation.getProductName(), orderRequest.getAmount(), false);
            }
        } else {
            log.info("No such product in stock");
            return new OrderDto(basicInformation.getCompanyName(), basicInformation.getProductName(), orderRequest.getAmount(), false);
        }

    }
}
