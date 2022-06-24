package com.autobots.automanager.controller.dtos;

import com.autobots.automanager.entity.Product;

import lombok.Data;

@Data
public class CreateProductDTO {
    public String _socialReason;
    public Long _userId;
    public Product _product;
  
    public String _expDate;
    public String _fabDateText;
}