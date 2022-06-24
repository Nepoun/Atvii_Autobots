package com.autobots.automanager.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Vehicle;
import com.autobots.automanager.repository.UserRepository;
import com.autobots.automanager.repository.VehicleRepository;
import com.autobots.automanager.service.UserService;

@RestController
@RequestMapping("/veiculo")
public class VehicleController {

    @Autowired
    private UserService _UserService;

    @Autowired
    private VehicleRepository _vehicleRepository;

    @Autowired
    private UserRepository _userRepository;

    @PostMapping("/register/{userId}")
    public ResponseEntity<?> createVehicle(@RequestBody List<Vehicle> vehicles, @PathVariable Long userId) {
        try {
            _UserService.registerVehicle(vehicles, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getVehicles() {
        List<Vehicle> vehicles = _vehicleRepository.findAll();

        if (vehicles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicle = _vehicleRepository.findById(id);

        if (vehicle.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Vehicle>(vehicle.get(), HttpStatus.FOUND);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Set<Vehicle>> getUserVehicle(@PathVariable Long id) {
        User user = _UserService.findUser(id);

        if (user == null || user.get_vehicles().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Set<Vehicle>>(user.get_vehicles(), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{userId}/{VehicleLicensePlate}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long userId, @PathVariable String VehicleLicensePlate) {
        User user = _UserService.findUser(userId);

        Vehicle vehicle = _UserService.findVehicle(user, VehicleLicensePlate);

        if (user == null || vehicle == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            user.get_vehicles().remove(vehicle);
            _vehicleRepository.deleteById(vehicle.get_id());
            _userRepository.save(user);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}