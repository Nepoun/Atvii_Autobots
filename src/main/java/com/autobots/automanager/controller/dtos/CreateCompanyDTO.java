package com.autobots.automanager.controller.dtos;

import java.util.List;

import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.Address;
import com.autobots.automanager.entity.Phone;

import lombok.Data;

@Data
public class CreateCompanyDTO {
  private Company _company;
  private Address _address;
  private List<Phone> _phones;
}