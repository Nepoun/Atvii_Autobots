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

import com.autobots.automanager.main.entity.Address;
import com.autobots.automanager.main.model.AddressSelector;
import com.autobots.automanager.main.repository.AddressRepository;
import com.autobots.automanager.main.model.AddressUpdater;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressRepository _repository;
	@Autowired
	private AddressSelector _selector;

	@GetMapping("/address/{id}")
	public Address getAddress(@PathVariable long id) {
		List<Address> addresss = _repository.findAll();
		return _selector.selectAdresses(addresss, id);
	}

	@GetMapping("/addresss")
	public List<Address> getAddresss() {
		List<Address> addresss = _repository.findAll();
		return addresss;
	}

	@PostMapping("/register")
	public void registerAddress(@RequestBody Address address) {
		_repository.save(address);
	}

	@PutMapping("/update")
	public void atualizarCliente(@RequestBody Address updatedAddress) {
		Address address = _repository.getById(updatedAddress.get_id());
		AddressUpdater updater = new AddressUpdater();
		updater.update(address, updatedAddress);
		_repository.save(address);
	}

	@DeleteMapping("/delete")
	public void deleteAddress(@RequestBody Address deleteAddress) {
		Address address = _repository.getById(deleteAddress.get_id());
		_repository.delete(address);
	}
}