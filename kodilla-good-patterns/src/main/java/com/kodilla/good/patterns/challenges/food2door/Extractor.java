package com.kodilla.good.patterns.challenges.food2door;

public interface Extractor {
    CompanyInfo extractData(CompaniesRepository database, OrderRequest orderRequest);
}
