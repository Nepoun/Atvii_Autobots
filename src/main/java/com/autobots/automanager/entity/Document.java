package com.autobots.automanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.autobots.automanager.enumerator.DocumentType;

import lombok.Data;

@Data
@Entity
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column(unique = true, nullable = false)
	private String _number;
	@Column(nullable = false)
	private DocumentType type;
	@Column(nullable = false)
	private Date _emissionDate;
}