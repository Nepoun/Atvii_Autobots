package com.autobots.automanager.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.main.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}