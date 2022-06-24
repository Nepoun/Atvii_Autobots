package com.autobots.automanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}