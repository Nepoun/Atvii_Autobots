package com.autobots.automanager.service;

import java.util.Optional;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Company;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Vehicle;
import com.autobots.automanager.model.updater.UserUpdater;
import com.autobots.automanager.repository.CompanyRepository;
import com.autobots.automanager.repository.UserRepository;
import com.autobots.automanager.repository.VehicleRepository;

@Service
public class UserService {
    @Autowired
    CompanyRepository _companyRepository;

    @Autowired
    UserRepository _userRepository;

    @Autowired
    VehicleRepository _vehicleRepository;
    @Autowired
    CompanyService _companyService;
  
    public User findUser(Long id) {
      Optional<User> user = _userRepository.findById(id);
  
      if (user.isEmpty()) {
        return null;
      }
  
      return user.get();
    }
  
    public void registerVehicle(List<Vehicle> vehicles, Long userId) {
      User client = findUser(userId);
  
      vehicles.forEach(vehicle -> {
        vehicle.set_owner(client);
        client.get_vehicles().add(vehicle);
  
        _vehicleRepository.save(vehicle);
      });
    }
  
    public Vehicle findVehicle(User user, String vehicleLicensePlate) {
      Set<Vehicle> vehicles = user.get_vehicles();
  
      Vehicle vehicleFound = null;
  
      for (Vehicle vehicle : vehicles) {
        if (vehicle.get_VehicleLicensePlate().equals(vehicleLicensePlate)) {
          vehicleFound = vehicle;
        }
      }
  
      return vehicleFound;
    }
  
    public void updateUser(User updatedUser) {
      User user = findUser(updatedUser.get_id());
  
      if (user == null) {
        new Exception("user not found");
      }
  
      UserUpdater updater = new UserUpdater();
      updater.update(user, updatedUser);
  
      _userRepository.save(user);
    }
  
    public void deleteUser(Long companyId, Long userId) {
      Company company = _companyService.findCompany(companyId);
  
      if (company == null) {
        new Exception("Company not found");
      }
  
      Set<User> users = company.get_users();
  
      User user = null;
      for (User IteratedUser : users) {
        if (IteratedUser.get_id() == userId) {
          user = IteratedUser;
        }
      }
  
      users.remove(users);
      _userRepository.deleteById(user.get_id());
      _companyRepository.save(company);
  
    }
  }