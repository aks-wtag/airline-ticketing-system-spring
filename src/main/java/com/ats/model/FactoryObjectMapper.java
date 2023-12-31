package com.ats.model;

import com.ats.model.airline.Airline;
import com.ats.model.airline.AirlineInput;
import com.ats.model.booking.Booking;
import com.ats.model.booking.BookingInput;
import com.ats.model.flight.Flight;
import com.ats.model.flight.FlightInput;
import com.ats.model.user.Passenger;
import com.ats.model.user.PassengerInput;
import com.ats.model.user.PassengerOutput;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FactoryObjectMapper {
    public static Passenger convertPassengerInputToModel(PassengerInput passengerInput){
        Passenger passenger = new Passenger();
        passenger.setUserFullName(passengerInput.getUserFullName());
        passenger.setUserEmail(passengerInput.getUserEmail());
        passenger.setUserPassword(passengerInput.getUserPassword());
        passenger.setUserContact(passengerInput.getUserContact());
        passenger.setPassengerPassport(passengerInput.getPassengerPassport());
        return passenger;
    }

    public static PassengerOutput convertPassengerEntityToPassengerOutput(Passenger passenger){
        PassengerOutput passengerOutput = new PassengerOutput();
        passengerOutput.setUserId(passenger.getUserId());
        passengerOutput.setUserFullName(passenger.getUserFullName());
        passengerOutput.setUserEmail(passenger.getUserEmail());
        passengerOutput.setUserContact(passenger.getUserContact());
        passengerOutput.setPassengerPassport(passenger.getPassengerPassport());

        return passengerOutput;
    }

    public static Airline convertAirlineInputToModel(AirlineInput airlineInput){
        Airline airline = new Airline();
        airline.setAirlineId(airlineInput.getAirlineId());
        airline.setAirlineName(airlineInput.getAirlineName());
        airline.setNumberOfSeats(airlineInput.getNumberOfSeats());
        return airline;
    }


    public static Flight convertFlightInputToModel(FlightInput flightInput){
        Flight flight = new Flight();

        flight.setRemainingSeats(flightInput.getAirline().getNumberOfSeats());
        flight.setFare(flightInput.getFare());
        flight.setAirline(flightInput.getAirline());
        flight.setDepartureDate(flightInput.getDepartureDate());
        flight.setArrivalDate(flightInput.getArrivalDate());
        flight.setDepartureLocation(flightInput.getDepartureLocation());
        flight.setArrivalLocation(flightInput.getArrivalLocation());
        flight.setDepartureTime(flightInput.getDepartureTime());
        flight.setArrivalTime(flightInput.getArrivalTime());

        return flight;
    }

    public static Booking convertBookingInputToModel(BookingInput bookingInput) {
        Booking booking = new Booking();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        booking.setBookingDate(dtf.format(now));
        booking.setBookedSeats(bookingInput.getBookedSeats());
        booking.setBookingAmount(bookingInput.getFlight().getFare()*bookingInput.getBookedSeats());
        booking.setFlight(bookingInput.getFlight());
        booking.setPassenger(bookingInput.getPassenger());

        return booking;
    }
}
