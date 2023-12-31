package com.ats.controller;

import com.ats.exception.BadRequestException;
import com.ats.model.airline.Airline;
import com.ats.model.airline.AirlineInput;
import com.ats.model.FactoryObjectMapper;
import com.ats.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@Transactional
public class AirlineController {
    AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }


    @PostMapping(value = "/airlines", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Airline> addAirline(@Valid @RequestBody AirlineInput airlineInput, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }

        Airline airline = FactoryObjectMapper.convertAirlineInputToModel(airlineInput);
        return new ResponseEntity<>(airlineService.addAirline(airline), HttpStatus.CREATED);
    }

    @GetMapping(value = "/airlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Airline>> getAirlines(){
        List<Airline> airlines = airlineService.getAllAirline();
        return ResponseEntity.ok(airlines);
    }

    @PutMapping(value = "/airlines/{airlineId}")
    public ResponseEntity<Airline> updateAirline(@PathVariable String airlineId, @RequestBody  Airline airline, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }

//        Airline airline = FactoryObjectMapper.convertAirlineInputToModel(airlineInput);
        Airline modifiedAirline = airlineService.updateAirline(airlineId, airline);
        return new ResponseEntity<>(modifiedAirline, HttpStatus.OK);
    }

    @DeleteMapping(value = "/airlines/{airlineId}")
    public ResponseEntity<Void> deleteAirline(@PathVariable String airlineId){
        airlineService.deleteAirline(airlineId);
        return ResponseEntity.noContent().build();
    }
}
