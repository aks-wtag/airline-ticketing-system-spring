package com.ats.controller;


import com.ats.model.FactoryObjectMapper;
import com.ats.model.booking.Booking;
import com.ats.model.booking.BookingInput;
import com.ats.model.flight.Flight;
import com.ats.model.user.Passenger;
import com.ats.service.BookingService;
import com.ats.service.FlightService;
import com.ats.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/passengers")
public class BookingController {
    BookingService bookingService;
    UserService userService;
    FlightService flightService;

    @Autowired
    public BookingController(BookingService bookingService, UserService userService, FlightService flightService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.flightService = flightService;
    }


    @PostMapping(value = "/{passengerId}/bookings")
    public ResponseEntity<Booking> bookTicket(@PathVariable int passengerId, @Valid @RequestBody BookingInput bookingInput) {
        Passenger passenger = userService.getPassenger(passengerId);
        bookingInput.setPassenger(passenger);
        Flight flight = flightService.getFlight(bookingInput.getFlightId());
        bookingInput.setFlight(flight);

        Booking booking = FactoryObjectMapper.convertBookingInputToModel(bookingInput);
        return new ResponseEntity<>(bookingService.bookTicket(booking), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{passengerId}/bookings")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable int passengerId){
        return ResponseEntity.ok(bookingService.getBookings(passengerId));
    }


    @GetMapping(value = "/bookings")
    public ResponseEntity<List<Booking>> getAllBookings(){
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping(value = "{passengerId}/bookings/{bookingId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable int passengerId, @PathVariable int bookingId){
        bookingService.deleteTicket(passengerId, bookingId);
        return ResponseEntity.noContent().build();
    }
}
