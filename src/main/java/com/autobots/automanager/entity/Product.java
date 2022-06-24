package com.autobots.automanager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column(nullable = false)
	private Date _expDate;
	@Column(nullable = false)
	private Date _fabDate;
	@Column(nullable = false)
	private Date _regisDate;
	@Column(nullable = false)
	private String _productName;
	@Column(nullable = false)
	private long _quantity;
	@Column(nullable = false)
	private double _value;
	@Column()
	private String _description;
}