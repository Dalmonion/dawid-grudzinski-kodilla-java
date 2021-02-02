package com.kodilla.good.patterns.challenges.Food2Door;

public class CompanyInfo {

    final private String companyName;
    final private String productName;
    final private int productAmount;

    public CompanyInfo(final String companyName, final String productName, final int productAmount) {
        this.companyName = companyName;
        this.productName = productName;
        this.productAmount = productAmount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductAmount() {
        return productAmount;
    }
}
