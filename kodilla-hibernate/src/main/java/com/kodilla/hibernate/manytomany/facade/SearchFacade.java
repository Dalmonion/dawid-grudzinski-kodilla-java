package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchFacade {

    private final CompanyDao companyDao;
    private final EmployeeDao employeeDao;

    @Autowired
    public SearchFacade(CompanyDao companyDao, EmployeeDao employeeDao) {
        this.companyDao = companyDao;
        this.employeeDao = employeeDao;
    }

    public List<Company> findCompaniesByAnyFragment(String fragment) {
        String input = "%" + fragment + "%";
        return companyDao.retrieveCompaniesWithTheFragment(input);
    }

    public List<Employee> findEmployeesByFragment(String fragment) {
        String input = "%" + fragment + "%";
        return employeeDao.retrieveEmployeesWithFragment(input);
    }
}
