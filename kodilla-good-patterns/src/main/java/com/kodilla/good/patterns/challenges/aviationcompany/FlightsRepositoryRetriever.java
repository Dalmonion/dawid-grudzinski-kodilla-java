package com.kodilla.good.patterns.challenges.aviationcompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class FlightsRepositoryRetriever {

    public List<Flight> retrieve() {
        List<Flight> database = new ArrayList<>();


        database.add(new Flight(Location.WARSZAWA.toString(), Location.GDANSK.toString(), 1));
        database.add(new Flight(Location.WARSZAWA.toString(), Location.KATOWICE.toString(), 2));
        database.add(new Flight(Location.WARSZAWA.toString(), Location.KRAKOW.toString(),3));
        database.add(new Flight(Location.WARSZAWA.toString(), Location.POZNAN.toString(), 4));
        database.add(new Flight(Location.RZESZOW.toString(), Location.KRAKOW.toString(), 5));
        database.add(new Flight(Location.RZESZOW.toString(), Location.KATOWICE.toString(), 6));
        database.add(new Flight(Location.RZESZOW.toString(), Location.WROCLAW.toString(), 7));
        database.add(new Flight(Location.KRAKOW.toString(), Location.RZESZOW.toString(), 8));
        database.add(new Flight(Location.KATOWICE.toString(), Location.RZESZOW.toString(), 9));



        return new ArrayList<>(database);
    }

}
