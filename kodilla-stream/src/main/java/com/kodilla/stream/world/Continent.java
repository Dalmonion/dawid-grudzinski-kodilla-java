package com.kodilla.stream.world;

import java.util.ArrayList;
import java.util.List;

public class Continent {


    private final List<Country> continentCountries = new ArrayList<>();

    public void addCountry(Country country) {
        continentCountries.add(country);
    }

    public List<Country> getContinentCountries() {
        return continentCountries;
    }
}
