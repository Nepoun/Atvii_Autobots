package com.autobots.automanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CredentialUserPassword extends Credential {
  @Column(nullable = false, unique = true)
  private String _username;
  @Column(nullable = false)
  private String _password;
}