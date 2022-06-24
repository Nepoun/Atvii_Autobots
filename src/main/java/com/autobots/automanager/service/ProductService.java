package com.autobots.automanager.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controller.dtos.CreateCompanyDTO;
import com.autobots.automanager.controller.dtos.CreateProductDTO;
import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.Product;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.CompanyRepository;
import com.autobots.automanager.repository.ProductRepository;
import com.autobots.automanager.repository.UserRepository;

@Service
public class ProductService {

    @Autowired
    CompanyRepository _companyRepository;

    @Autowired
    UserRepository _userRepository;

    @Autowired
    ProductRepository _productRepository;

    @Autowired
    CompanyService _companyService;

    @Autowired
    UserService _userService;

    public void createProduct(CreateProductDTO productDTO, String socialReason) {
        Company company = _companyService.findCompany(socialReason);

        if (company == null) {
            new Exception("Company not found");
        }

        productDTO._product.set_regisDate(new Date());
        productDTO._product.set_fabDate(new Date(productDTO._fabDateText));
        productDTO._product.set_expDate(new Date(productDTO._expDate));

        company.get_products().add(productDTO._product);

        _companyRepository.save(company);
    }

    public void createProduct(CreateProductDTO productDTO, Long providerId) {
        User user = _userService.findUser(providerId);

        if (user == null) {
            new Exception("Provider not found");
        }

        productDTO._product.set_regisDate(new Date());
        productDTO._product.set_fabDate(new Date(productDTO._fabDateText));
        productDTO._product.set_expDate(new Date(productDTO._expDate));

        user.get_products().add(productDTO._product);

        _userRepository.save(user);
    }

    public Product findProduct(Long id) {
        Optional<Product> product = _productRepository.findById(id);

        if (product.isEmpty()) {
            return null;
        }
        return product.get();
    }

    public void deleteProduct(String socialReason, Long productId) {
        Company company = _companyService.findCompany(socialReason);

        if (company == null) {
            new Exception("Company not found");
        }

        Set<Product> products = company.get_products();

        Product product = null;
        for (Product iteratedProduct : products) {
            if (iteratedProduct.get_id() == productId) {
                product = iteratedProduct;
            }
        }

        products.remove(product);
        _productRepository.deleteById(productId);
        _companyRepository.save(company);
    }

    public void deleteProduct(Long providerId, Long productId) {
        User user = _userService.findUser(providerId);

        if (user == null) {
            new Exception("Provider not found");
        }

        Set<Product> products = user.get_products();

        Product product = null;
        for (Product iteratedProduct : products) {
            if (iteratedProduct.get_id() == productId) {
                product = iteratedProduct;
            }
        }

        products.remove(product);
        _productRepository.deleteById(product.get_id());
        _userRepository.save(user);
    }
}
