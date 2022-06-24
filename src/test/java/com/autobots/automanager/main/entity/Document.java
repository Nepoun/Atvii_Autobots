package com.autobots.automanager.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column
	private String _type;
	@Column(unique = true)
	private String _number;
}