package com.autobots.automanager.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entity.Sale;
import com.autobots.automanager.repository.SaleRepository;
import com.autobots.automanager.service.SaleService;

@RestController
@RequestMapping("/sales")
public class SaleController {

  @Autowired
  private SaleService _service;

  @Autowired
  private SaleRepository _repository;

  @PostMapping("/Register/{companyId}")
  public ResponseEntity<Sale> CreateSale(@RequestBody Sale sale, @PathVariable Long companyId) {
    try {
      _service.createSale(sale, companyId);

      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/")
  public ResponseEntity<List<Sale>> getSales() {
    List<Sale> sales = _repository.findAll();

    if (sales.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(sales, HttpStatus.FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Sale> getSale(@PathVariable Long id) {
    Sale sale = _service.findSale(id);

    if (sale == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Sale>(sale, HttpStatus.FOUND);
  }

  @GetMapping("company/{companyId}")
  public ResponseEntity<Set<Sale>> findCompanySales(@PathVariable Long companyId) {
    Set<Sale> sales = _service.findCompanySale(companyId);

    if (sales.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Set<Sale>>(sales, HttpStatus.FOUND);
  }
}