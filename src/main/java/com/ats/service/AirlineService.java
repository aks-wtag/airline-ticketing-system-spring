package com.ats.service;

import com.ats.model.airline.Airline;

import java.util.List;

public interface AirlineService {
    Airline addAirline(Airline airline);
    Airline updateAirline(String airlineId, Airline airline);
    void deleteAirline(String airlineId);
    List<Airline> getAllAirline();
    Airline getAirline(String airlineId);
}
