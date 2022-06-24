package com.autobots.automanager.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.main.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}