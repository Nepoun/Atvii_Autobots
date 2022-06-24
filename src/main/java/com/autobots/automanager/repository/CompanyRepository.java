package com.autobots.automanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

  Optional<Company> findBySocialReason(String _socialReason);

}