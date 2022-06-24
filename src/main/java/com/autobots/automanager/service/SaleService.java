package com.autobots.automanager.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.Product;
import com.autobots.automanager.entity.ServiceEntity;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Vehicle;
import com.autobots.automanager.entity.Sale;
import com.autobots.automanager.repository.CompanyRepository;
import com.autobots.automanager.repository.SaleRepository;

@Service
public class SaleService {

  @Autowired
  SaleRepository _saleRepository;

  @Autowired
  CompanyRepository _companyRepository;

  @Autowired
  CompanyService _companyService;

  @Autowired
  UserService _userService;

  @Autowired
  ProductService _productService;

  @Autowired
  ServiceCompanyService _serviceCompany_Service;

  public void createSale(Sale sale, Long companyId) {
    Company company = _companyService.findCompany(companyId);

    if (company == null) {
      new Exception("Company not found");
    }

    User client = _userService.findUser(sale.get_client().get_id());
    User functionary = _userService.findUser(sale.get_functionary().get_id());
    Vehicle vehicle = _userService.findVehicle(client, sale.get_vehicle().get_VehicleLicensePlate());

    for (Product productItem : sale.get_products()) {
      Product product = _productService.findProduct(productItem.get_id());
      productItem = product;
    }

    for (ServiceEntity serviceItem : sale.get_services()) {
      ServiceEntity service = _serviceCompany_Service.findService(serviceItem.get_id());
      serviceItem = service;
    }

    sale.set_client(client);
    sale.set_functionary(functionary);
    sale.set_vehicle(vehicle);
    sale.set_registration(new Date());

    vehicle.get_sales().add(sale);
    company.get_sales().add(sale);

    _companyRepository.save(company);
  }

  public Sale findSale(Long id) {
    Optional<Sale> sale = _saleRepository.findById(id);

    if (sale.isEmpty()) {
      return null;
    }

    return sale.get();
  }

  public Set<Sale> findCompanySale(Long companyId) {
    Company company = _companyService.findCompany(companyId);

    if (company == null) {
      new Exception("Company not found");
    }

    return company.get_sales();
  }
}