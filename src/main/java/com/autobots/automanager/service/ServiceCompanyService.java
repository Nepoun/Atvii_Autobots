package com.autobots.automanager.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.ServiceEntity;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.CompanyRepository;
import com.autobots.automanager.repository.ServiceRepository;

@Service
public class ServiceCompanyService {

  @Autowired
  ServiceRepository _serviceRepository;

  @Autowired
  CompanyRepository _companyRepository;

  @Autowired
  CompanyService _companyService;

  @Autowired
  UserService _userService;

  public void createService(ServiceEntity service, String socialReason) {
    Company company = _companyService.findCompany(socialReason);

    if (company == null) {
      new Exception("company not found");
    }

    company.get_services().add(service);

    _companyRepository.save(company);
  }

  public ServiceEntity findService(Long id) {
    Optional<ServiceEntity> service = _serviceRepository.findById(id);

    if (service.isEmpty()) {
      return null;
    }

    return service.get();
  }

  public void DeleteService(String socialReason, Long serviceId) {
    Company company = _companyService.findCompany(socialReason);

    if (company == null) {
      new Exception("company not found");
    }

    Set<ServiceEntity> services = company.get_services();

    ServiceEntity service = null;
    for (ServiceEntity iteratedService : services) {
      if (iteratedService.get_id() == serviceId) {
        service = iteratedService;
      }
    }

    services.remove(service);
    _serviceRepository.deleteById(service.get_id());
    _companyRepository.save(company);
  }
}
