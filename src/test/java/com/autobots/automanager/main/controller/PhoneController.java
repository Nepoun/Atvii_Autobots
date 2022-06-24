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

import com.autobots.automanager.main.entity.Phone;
import com.autobots.automanager.main.model.*;
import com.autobots.automanager.main.repository.PhoneRepository;

@RestController
@RequestMapping("/phone")
public class PhoneController {
	@Autowired
	private PhoneRepository _repository;
	@Autowired
	private PhoneSelector _selector;

	@GetMapping("/phone/{id}")
	public Phone getPhone(@PathVariable long id) {
		List<Phone> phones = _repository.findAll();
		return _selector.selectPhones(phones, id);
	}

	@GetMapping("/phones")
	public List<Phone> getPhones() {
		List<Phone> phones = _repository.findAll();
		return phones;
	}

	@PostMapping("/register")
	public void registerPhone(@RequestBody Phone phone) {
		_repository.save(phone);
	}

	@PutMapping("/update")
	public void atualizarCliente(@RequestBody Phone updatedPhone) {
		Phone phone = _repository.getById(updatedPhone.get_id());
		PhoneUpdater updater = new PhoneUpdater();
		updater.update(phone, updatedPhone);
		_repository.save(phone);
	}

	@DeleteMapping("/delete")
	public void deletePhone(@RequestBody Phone deletePhone) {
		Phone phone = _repository.getById(deletePhone.get_id());
		_repository.delete(phone);
	}
}