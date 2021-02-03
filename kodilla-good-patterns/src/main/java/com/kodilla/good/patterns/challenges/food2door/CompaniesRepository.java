package com.kodilla.good.patterns.challenges.food2door;

import java.util.List;

public class CompaniesRepository {

    private List<Company> companyList;

    public CompaniesRepository(List<Company> companyList) {
        this.companyList = companyList;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }
}
