package com.autobots.automanager.controller;

import java.security.Provider.Service;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.ServiceEntity;
import com.autobots.automanager.service.CompanyService;
import com.autobots.automanager.service.ServiceCompanyService;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    public ServiceCompanyService _serviceCompany_Service;

    @Autowired
    public CompanyService _companyService;

    @PostMapping("/create/{socialReason}")
    public ResponseEntity<?> serviceRegister(@RequestBody ServiceEntity service, @PathVariable String socialReason) {
        try {
            _serviceCompany_Service.createService(service, socialReason);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getService(@PathVariable Long id) {
        ServiceEntity service = _serviceCompany_Service.findService(id);

        if (service == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<ServiceEntity>(service, HttpStatus.FOUND);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<Set<ServiceEntity>> get_services(@PathVariable Long companyId) {
        Company company = _companyService.findCompany(companyId);

        if (company == null || company.get_services().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Set<ServiceEntity>>(company.get_services(), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{socialReason}/{idService}")
    public ResponseEntity<ServiceEntity> DeleteCompanyService(
            @PathVariable String socialReason,
            @PathVariable Long serviceId) {

        try {
            _serviceCompany_Service.DeleteService(socialReason, serviceId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}