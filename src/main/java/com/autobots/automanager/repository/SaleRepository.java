package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    
}
