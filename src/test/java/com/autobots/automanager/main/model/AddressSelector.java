package com.autobots.automanager.main.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.main.entity.Address;

@Component
public class AddressSelector {
    public Address selectAdresses(List<Address> addresses, long id) {
        Address selected = null;
        for (Address address : addresses) {
            if (address.get_id() == id) {
                selected = address;
            }
        }
        return selected;
    }
}