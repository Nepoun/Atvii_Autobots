package com.autobots.automanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends RepresentationModel<Address>{
    @Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column(nullable = true)
	private String _state;
	@Column(nullable = false)
	private String _city;
	@Column(nullable = true)
	private String _district;
	@Column(nullable = false)
	private String _street;
	@Column(nullable = false)
	private String _number;
}
