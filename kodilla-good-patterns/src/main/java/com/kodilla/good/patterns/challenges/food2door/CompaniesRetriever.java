package com.kodilla.good.patterns.challenges.food2door;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompaniesRetriever {

    List<Company> companies;

    public CompaniesRetriever() {
        this.companies = new ArrayList<>();

        Company extraFoodShop = new ExtraFoodShop("ExtraFoodShop");
        extraFoodShop.addGoods(new Item("Corn"), 100);

        Company healthyShop = new HealthyShop("HealthyShop");
        healthyShop.addGoods(new Item("Apple"), 100);

        Company glutenFreeShop = new GlutenFreeShop("GlutenFreeShop");
        glutenFreeShop.addGoods(new Item("Orange"), 100);

        companies.add(extraFoodShop);
        companies.add(healthyShop);
        companies.add(glutenFreeShop);
    }

    public List<Company> retrieve() {
        return new LinkedList<>(companies);
    }
}
