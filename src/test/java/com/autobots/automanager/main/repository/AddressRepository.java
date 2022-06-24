package com.autobots.automanager.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.main.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}