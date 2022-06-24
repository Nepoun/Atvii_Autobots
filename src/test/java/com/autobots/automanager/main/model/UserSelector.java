package com.autobots.automanager.main.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.main.entity.User;

@Component
public class UserSelector {
    public User selectUsers(List<User> users, long id) {
        User selected = null;
        for (User user : users) {
            if (user.get_id() == id) {
                selected = user;
            }
        }
        return selected;
    }
}