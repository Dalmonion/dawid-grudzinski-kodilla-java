package com.kodilla.stream.world;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTestSuite {

    @Test
    void testGetPeopleQuantity() {
        // Given
        Country Poland = new Country(new BigDecimal("12345678901234567899"));
        Country Germany = new Country(new BigDecimal("12345678901234567898"));
        Country Spain = new Country(new BigDecimal("12345678901234567897"));
        Continent europe = new Continent();
        europe.addCountry(Poland);
        europe.addCountry(Germany);
        europe.addCountry(Spain);

        Country Russia = new Country(new BigDecimal("12345678901234567896"));
        Country China = new Country(new BigDecimal("12345678901234567895"));
        Country India = new Country(new BigDecimal("12345678901234567894"));
        Continent asia = new Continent();
        asia.addCountry(Russia);
        asia.addCountry(China);
        asia.addCountry(India);

        Country Argentina = new Country(new BigDecimal("12345678901234567893"));
        Country Brazil = new Country(new BigDecimal("12345678901234567892"));
        Country Peru = new Country(new BigDecimal("12345678901234567891"));
        Continent southAmerica = new Continent();
        southAmerica.addCountry(Argentina);
        southAmerica.addCountry(Brazil);
        southAmerica.addCountry(Peru);

        World world = new World();
        world.addContinent(europe);
        world.addContinent(asia);
        world.addContinent(southAmerica);

        // When
        BigDecimal totalPopulation = world.getPeopleQuantity();

        // Then
        BigDecimal expectedPeopleQuantity = new BigDecimal("111111110111111111055");
        assertEquals(expectedPeopleQuantity, totalPopulation);
    }
}
