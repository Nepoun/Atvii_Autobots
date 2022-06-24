package com.autobots.automanager.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.main.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}