package com.ats.dao;

import com.ats.model.airline.Airline;

import java.util.List;

public interface AirlineDao {
    Airline saveAirline(Airline airline);
    List<Airline> viewAirlines();
    void deleteAirline(Airline airline);
    Airline getAirline(String airlineId);
}
