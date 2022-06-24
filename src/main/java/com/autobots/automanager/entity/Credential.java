package com.autobots.automanager.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credential {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	@Column(nullable = false)
	private Date _creationDate;
	@Column()
	private Date _lastAccess;
	@Column(nullable = false)
	private boolean _inactive;
}