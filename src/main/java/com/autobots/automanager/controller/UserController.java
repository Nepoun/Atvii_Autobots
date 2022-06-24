package com.autobots.automanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.model.LinkAdd.UserLinkAdder;
import com.autobots.automanager.repository.UserRepository;
import com.autobots.automanager.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository _repository;

    @Autowired
    UserService _service;

    @Autowired
    UserLinkAdder _linkAdder;

    @GetMapping("/")
    public ResponseEntity<List<User>> GetUsers() {
        List<User> _users = _repository.findAll();
        if (_users.isEmpty()) {
            ResponseEntity<List<User>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return response;
        } else {
            _linkAdder.AddLink(_users);
            ResponseEntity<List<User>> response = new ResponseEntity<>(_users, HttpStatus.FOUND);
            return response;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = _service.findUser(id);

        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        _linkAdder.AddLink(user);
        return new ResponseEntity<User>(user, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            _service.updateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/Delete/{companyId}/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Long companyId, @PathVariable Long userId) {
        try {
            _service.deleteUser(companyId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}