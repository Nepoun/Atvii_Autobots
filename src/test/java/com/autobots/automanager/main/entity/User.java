package com.autobots.automanager.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column
	private String _name;
	@Column
	private String _socialName;
	@Column
	private Date _birthDate;
	@Column
	private Date _registrationDate;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Document> _document = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Phone> phones = new ArrayList<>();

}