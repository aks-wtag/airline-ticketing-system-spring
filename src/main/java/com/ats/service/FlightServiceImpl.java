package com.ats.service;

import com.ats.dao.AirlineDao;
import com.ats.dao.FlightDao;
import com.ats.model.flight.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
public class FlightServiceImpl implements FlightService{
    FlightDao flightDao;
    AirlineDao airlineDao;

    @Autowired
    public FlightServiceImpl(FlightDao flightDao, AirlineDao airlineDao) {
        this.flightDao = flightDao;
        this.airlineDao = airlineDao;
    }

    @Override
    public Flight addFlight(Flight flight) {
        return flightDao.saveFlight(flight);
    }

    @Override
    public Flight getFlight(int flightId) {
        Flight flight = flightDao.getFlight(flightId);
        if(Objects.isNull(flight)){
            throw new NoSuchElementException("Flight not found");
        }
        return flight;
    }


    @Override
    public List<Flight> getAllFlights() {
        return flightDao.getFlights();
    }

    @Override
    public List<Flight> getFilteredFlights(Map<String, String> filterFields) {

        String departureLocation = filterFields.get("departureLocation");
        String arrivalLocation = filterFields.get("arrivalLocation");
        String departureDate = filterFields.get("departureDate");

        return flightDao.getFilteredFlights(departureLocation, arrivalLocation, departureDate);
    }

    @Override
    public Flight updateFlight(int flightId, Flight flight) {
        Flight dbFlight = flightDao.getFlight(flightId);
        if(Objects.isNull(dbFlight)){
            throw new NoSuchElementException("Flight or Airline not found");
        }

        dbFlight.setFare(flight.getFare());
        dbFlight.setAirline(flight.getAirline());
        dbFlight.setDepartureDate(flight.getDepartureDate());
        dbFlight.setArrivalDate(flight.getArrivalDate());
        dbFlight.setDepartureLocation(flight.getDepartureLocation());
        dbFlight.setArrivalLocation(flight.getArrivalLocation());
        dbFlight.setDepartureTime(flight.getDepartureTime());
        dbFlight.setArrivalTime(flight.getArrivalTime());

        return flightDao.saveFlight(dbFlight);
    }

    @Override
    public void deleteFlight(int flightId) {
        Flight dbFlight = flightDao.getFlight(flightId);
        if(Objects.isNull(dbFlight)){
            throw new NoSuchElementException("Flight not found");
        }
        flightDao.deleteFlight(dbFlight);
    }
}
