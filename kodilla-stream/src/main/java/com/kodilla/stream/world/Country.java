package com.kodilla.stream.world;

import java.math.BigDecimal;

public class Country {

    private BigDecimal peopleQuantity;

    public Country(BigDecimal quantity) {

        BigDecimal input = quantity;
        peopleQuantity = input;
    }

    public BigDecimal getPeopleQuantity(){
        return peopleQuantity;
    }
}
