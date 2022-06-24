package com.autobots.automanager.main.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.main.entity.Phone;

@Component
public class PhoneSelector {
    public Phone selectPhones(List<Phone> phones, long id) {
        Phone selected = null;
        for (Phone phone : phones) {
            if (phone.get_id() == id) {
                selected = phone;
            }
        }
        return selected;
    }
}