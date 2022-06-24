package com.autobots.automanager.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
@Embeddable
public class Phone extends RepresentationModel<Phone> {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column
	private String _ddd;
	@Column
	private String _number;
	@Column
	private Long _clientId;
}