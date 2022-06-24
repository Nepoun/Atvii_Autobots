package com.autobots.automanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.controller.dtos.CreateCompanyDTO;
import com.autobots.automanager.entity.Company;
import com.autobots.automanager.model.LinkAdd.CompanyLinkAdder;
import com.autobots.automanager.repository.CompanyRepository;
import com.autobots.automanager.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    public CompanyService _companyService;

    @Autowired
    CompanyRepository _companyRepository;

    @Autowired
    CompanyLinkAdder _linkAdder;

    @PostMapping("/criar")
    public ResponseEntity<Company> createCompany(@RequestBody CreateCompanyDTO company) {

        try {
            Company newCompany = _companyService.CreateCompany(company);

            return new ResponseEntity<Company>(newCompany, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Company>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = _companyService.findCompany(id);

        if (company == null) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
        _linkAdder.AddLink(company);
        return new ResponseEntity<Company>(company, HttpStatus.FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = _companyRepository.findAll();
        if (companies.isEmpty()) {
            ResponseEntity<List<Company>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return response;
        } else {
            _linkAdder.AddLink(companies);
            ResponseEntity<List<Company>> response = new ResponseEntity<>(companies, HttpStatus.FOUND);
            return response;
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Company> update(@RequestBody Company updatedCompany) {
        try {
            _companyService.updateCompany(updatedCompany);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Company> delete(@PathVariable Long id) {
        try {
            _companyService.DeleteCompany(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}