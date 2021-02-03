package com.kodilla.good.patterns.challenges.food2door;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InformationExtractor implements Extractor {


    @Override
    public CompanyInfo extractData(CompaniesRepository database, OrderRequest orderRequest) {
        String companyName = "Unknown";
        String desiredProduct = orderRequest.getItem().getItemName();
        int amount = -1;

        for (Company company : database.getCompanyList()) {
            if (company.getCompanyGoods().containsKey(orderRequest.getItem())) {
                amount = company.getCompanyGoods().get(orderRequest.getItem());
                companyName = company.getCompanyName();
            }
        }

        log.info("Extracting basic information about supplier. \nCompany name which have desired product: " + companyName +
                ". Product count in stock: " + amount);

        return new CompanyInfo(companyName, desiredProduct, amount);


    }
}
