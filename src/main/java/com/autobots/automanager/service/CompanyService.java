package com.autobots.automanager.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controller.dtos.CreateCompanyDTO;
import com.autobots.automanager.entity.Company;
import com.autobots.automanager.model.company.CompanyUpdater;
import com.autobots.automanager.repository.CompanyRepository;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository _repository;

    public Company CreateCompany(CreateCompanyDTO companyDTO) {
        companyDTO.get_company().set_registration(new Date());
        companyDTO.get_company().set_registration(new Date());
        companyDTO.get_company().set_address(companyDTO.get_address());

        companyDTO.get_phones().forEach(newPhone -> {
            companyDTO.get_company().get_phones().add(newPhone);
        });

        Company newCompany = _repository.save(companyDTO.get_company());
        return newCompany;
    }

    public Company findCompany(String socialReason) {
        Optional<Company> company = _repository.findBySocialReason(socialReason);

        if (company.isEmpty()) {
            return null;
        }
        return company.get();
    }

    public Company findCompany(Long companyId) {
        Optional<Company> company = _repository.findById(companyId);

        if (company.isEmpty()) {
            return null;
        }
        return company.get();
    }

    public void updateCompany(Company updatedCompany) {
        Company company = findCompany(updatedCompany.get_id());

        if (company == null) {
            new Exception("Company not found");
        }

        CompanyUpdater updater = new CompanyUpdater();
        updater.atualizar(company, updatedCompany);

        _repository.save(company);
    }

    public void DeleteCompany(Long id) {
        Company company = findCompany(id);

        if (company == null) {
            new Exception("Company not found");
        }

        _repository.deleteById(company.get_id());
    }
}
