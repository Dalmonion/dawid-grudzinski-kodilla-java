package com.kodilla.good.patterns.challenges.food2door;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompaniesRetriever {

    List<Company> companies;

    public CompaniesRetriever() {
        this.companies = new ArrayList<>();

        Company extraFoodShop = new Company("ExtraFoodShop");
        extraFoodShop.addGoods(new Item("Corn", "ExtraFoodShop"), 100);

        Company healthyShop = new Company("HealthyShop");
        healthyShop.addGoods(new Item("Apple", "HealthyShop"), 100);

        Company glutenFreeShop = new Company("GlutenFreeShop");
        glutenFreeShop.addGoods(new Item("Orange", "GlutenFreeShop"), 100);

        companies.add(extraFoodShop);
        companies.add(healthyShop);
        companies.add(glutenFreeShop);
    }

    public List<Company> retrieve() {
        return new LinkedList<>(companies);
    }
}
