package com.autobots.automanager.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Address {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column(nullable = true)
	private String _state;
	@Column(nullable = false)
	private String _city;
	@Column(nullable = true)
	private String _neighboorhood;
	@Column(nullable = false)
	private String _street;
	@Column(nullable = false)
	private String _number;
	@Column(nullable = true)
	private String _postalCode;
}
