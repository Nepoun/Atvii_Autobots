package com.autobots.automanager.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.main.entity.User;
import com.autobots.automanager.main.model.UserUpdater;
import com.autobots.automanager.main.model.UserSelector;
import com.autobots.automanager.main.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository _repository;
	@Autowired
	private UserSelector _selector;

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable long id) {
		List<User> users = _repository.findAll();
		return _selector.selectUsers(users, id);
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		List<User> users = _repository.findAll();
		return users;
	}

	@PostMapping("/register")
	public void registerUser(@RequestBody User user) {
		_repository.save(user);
	}

	@PutMapping("/update")
	public void atualizarCliente(@RequestBody User updatedUser) {
		User user = _repository.getById(updatedUser.get_id());
		UserUpdater updater = new UserUpdater();
		updater.update(user, updatedUser);
		_repository.save(user);
	}

	@DeleteMapping("/delete")
	public void deleteUser(@RequestBody User deleteUser) {
		User user = _repository.getById(deleteUser.get_id());
		_repository.delete(user);
	}
}