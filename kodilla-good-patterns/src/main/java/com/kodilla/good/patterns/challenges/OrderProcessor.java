package com.kodilla.good.patterns.challenges;

public class OrderProcessor {

    private InformationService informationService;
    private OrderService orderService;


    public OrderProcessor(final InformationService informationService, final OrderService orderService) {
        this.informationService = informationService;
        this.orderService = orderService;
    }

    public OrderDto process(final OrderRequest orderRequest) {
        boolean canBuy = orderService.createOrder(orderRequest.getUser(), orderRequest.getItem());
        if (canBuy) {
            return new OrderDto(orderRequest.getUser(), true);

        } else {
            return new OrderDto(orderRequest.getUser(), false);
        }

    }
}
