package com.ats.controller;

import com.ats.exception.BadRequestException;
import com.ats.model.FactoryObjectMapper;
import com.ats.model.user.*;
import com.ats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/passengers")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<PassengerOutput> registrationDetails(@Valid @RequestBody PassengerInput passengerInput, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }

        Passenger passenger = FactoryObjectMapper.convertPassengerInputToModel(passengerInput);
        Passenger savedPassenger = userService.registerPassenger(passenger);
        return new ResponseEntity<>(FactoryObjectMapper.convertPassengerEntityToPassengerOutput(savedPassenger), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{passengerId}")
    public ResponseEntity<PassengerOutput> editPassengerDetails(@PathVariable int passengerId, @Valid @RequestBody PassengerInput passengerInput, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }

        Passenger passenger = FactoryObjectMapper.convertPassengerInputToModel(passengerInput);
        Passenger modifiedPassenger = userService.updatePassengerDetails(passengerId, passenger);
        return ResponseEntity.ok(FactoryObjectMapper.convertPassengerEntityToPassengerOutput(modifiedPassenger));
    }
}
