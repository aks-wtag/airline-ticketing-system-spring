package com.ats.dao;

import com.ats.model.flight.Flight;

import java.util.List;

public interface FlightDao {
    Flight saveFlight(Flight flight);
    List<Flight> getFlights();
    Flight getFlight(int flightId);
    List<Flight> getFilteredFlights(String departureLocation, String arrivalLocation, String departureDate);
    void deleteFlight(Flight flight);
}
