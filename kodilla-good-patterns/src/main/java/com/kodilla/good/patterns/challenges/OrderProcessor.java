package com.kodilla.good.patterns.challenges;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderProcessor {

    private final InformationService informationService;
    private final OrderService orderService;
    private final BuyRepository buyRepository;
    private final Warehouse warehouse;


    public OrderProcessor(final InformationService informationService,
                          final OrderService orderService, final BuyRepository buyRepository, final Warehouse warehouse) {
        this.informationService = informationService;
        this.orderService = orderService;
        this.buyRepository = buyRepository;
        this.warehouse = warehouse;
    }

    public OrderDto process(final OrderRequest orderRequest) {

        boolean canBuy = orderService.order(orderRequest.getUser(), orderRequest.getItem());
        boolean isInStock = warehouse.itemInStock(orderRequest.getItem());
        log.info("Checking if customer has enough funds to buy: " + canBuy);
        if (canBuy) {
            if (isInStock) {
                log.info(orderRequest.getUser().getUserName() + " has bought an item.");
                informationService.inform(orderRequest.getUser());
                buyRepository.createOrder(orderRequest.getUser(), orderRequest.getItem());
                return new OrderDto(orderRequest.getUser(), true, orderRequest.getItem().getPrice());
            } else {
                log.info("Item out of stock");
                return new OrderDto(orderRequest.getUser(), false, 0);
            }

        } else {
            log.info(orderRequest.getUser().getUserName() + " has insufficient funds.");
            return new OrderDto(orderRequest.getUser(), false, 0);
        }

    }
}
