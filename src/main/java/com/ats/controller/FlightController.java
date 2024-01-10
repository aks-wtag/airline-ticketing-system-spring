package com.ats.controller;

import com.ats.exception.BadRequestException;
import com.ats.model.airline.Airline;
import com.ats.model.FactoryObjectMapper;
import com.ats.model.flight.Flight;
import com.ats.model.flight.FlightInput;
import com.ats.service.AirlineService;
import com.ats.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class FlightController {
    FlightService flightService;
    AirlineService airlineService;

    @Autowired
    public FlightController(FlightService flightService, AirlineService airlineService) {
        this.flightService = flightService;
        this.airlineService = airlineService;
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@Valid @RequestBody FlightInput flightInput, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }
        Airline airline = airlineService.getAirline(flightInput.getAirlineId());

        flightInput.setAirline(airline);
        Flight flight = FactoryObjectMapper.convertFlightInputToModel(flightInput);

        return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlight(){
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping(value = "/searched-flights")
    public ResponseEntity<List<Flight>> getFilteredFlights(@RequestBody Map<String, String> filterFields){
        List<Flight> flights = flightService.getFilteredFlights(filterFields);
        return ResponseEntity.ok(flights);
    }

    @PutMapping(value = "/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable int flightId, @RequestBody FlightInput flightInput, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }
        Airline airline = airlineService.getAirline(flightInput.getAirlineId());

        flightInput.setAirline(airline);
        Flight flight = FactoryObjectMapper.convertFlightInputToModel(flightInput);


        Flight modifiedFlight = flightService.updateFlight(flightId, flight);

        return new ResponseEntity<>(modifiedFlight, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable int flightId){
        flightService.deleteFlight(flightId);
        return ResponseEntity.noContent().build();
    }
}
