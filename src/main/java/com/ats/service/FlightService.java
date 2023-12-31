package com.ats.service;

import com.ats.model.flight.Flight;

import java.util.List;
import java.util.Map;

public interface FlightService {
    Flight addFlight(Flight flight);
    Flight getFlight(int flightId);
    List<Flight> getAllFlights();
    List<Flight> getFilteredFlights(Map<String, String> filterFields);
    Flight updateFlight(int flightId, Flight flight);
    void deleteFlight(int flightId);
}
