package com.kodilla.good.patterns.challenges.Food2Door;

public interface Extractor {
    CompanyInfo extractData(CompaniesRepository database, OrderRequest orderRequest);
}
