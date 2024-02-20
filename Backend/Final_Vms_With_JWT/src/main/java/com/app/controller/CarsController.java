package com.app.controller;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CarRequestDTO;
import com.app.entity.Cars;
import com.app.service.CarsService;


@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarsController {

    @Autowired
    private CarsService carsService;
    
//    private ImageHandlingService imageHandlingService;



    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Long carId) {
        carsService.deleteCar(carId);
        return ResponseEntity.ok("Car deleted successfully");
    }

    @PutMapping("/{carId}")
    public ResponseEntity<Cars> updateCar(@PathVariable Long carId, @RequestBody CarRequestDTO carRequestDTO) {
        Cars updatedCar = carsService.updateCar(carId, carRequestDTO);
        if (updatedCar != null) {
            return new ResponseEntity<>(updatedCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


   
    @GetMapping("/by-brand-model")
    public ResponseEntity<Cars> getCarByBrandAndModelWithSpec(@RequestParam String brandName, @RequestParam String modelName) {
        Optional<Cars> car = carsService.getCarByBrandAndModelWithSpec(brandName, modelName);
        if (car.isPresent()) {
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   
   
        @PostMapping
        public ResponseEntity<Cars> createCar(@RequestBody CarRequestDTO carRequestDTO) throws IOException {
            Cars savedCar = carsService.createCar(carRequestDTO);
            return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
        }
        
    
        
        
        @GetMapping("/{carId}")
        public Cars getCarWithSpecById(@PathVariable Long carId) {
            return carsService.findCarWithSpecificationById(carId);
        }
        
        
        @GetMapping
        public List<Cars> getAllCarsWithSpec2() {
            return carsService.getAllCarsWithSpec2();
        }

        @GetMapping("/withoutspec")
        public List<Cars> getAllCarsWithoutSpec2() {
            return carsService.getAllCarsWithoutSpec2();
        }
        
        @GetMapping("/details")
        public List<Cars> getCarDetailsByBrandOrModel(
            @RequestParam(value = "brandName", required = false) String brandName,
            @RequestParam(value = "modelName", required = false) String modelName) {
            
            if (brandName != null) {
                // Search by brandName
                return carsService.getCarDetailsByBrandOrModel(brandName, null);
            } else if (modelName != null) {
                // Search by modelName
                return carsService.getCarDetailsByBrandOrModel(null, modelName);
            } else {
                // Both brandName and modelName are null, return an empty list or handle as needed.
                return Collections.emptyList();
            }
        }

        
    }


