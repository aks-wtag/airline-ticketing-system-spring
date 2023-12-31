package com.ats.service;

import com.ats.dao.AirlineDao;
import com.ats.model.airline.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AirlineServiceImpl implements AirlineService {
//    private AdminDao adminDao;
    private final AirlineDao airlineDao;

    @Autowired
    public AirlineServiceImpl(AirlineDao airlineDao) {
        this.airlineDao = airlineDao;
    }


    @Override
    public Airline addAirline(Airline airline) {
        return airlineDao.saveAirline(airline);
    }

    @Override
    public Airline updateAirline(String airlineId, Airline airline) {
        Airline dbAirline = airlineDao.getAirline(airlineId);
        if(Objects.nonNull(dbAirline)){
            if(airline.getAirlineName()!=null) dbAirline.setAirlineName(airline.getAirlineName());
            if(airline.getNumberOfSeats()!=0) dbAirline.setNumberOfSeats(airline.getNumberOfSeats());
            return airlineDao.saveAirline(dbAirline);
        }
        else {
            throw new NoSuchElementException("Airline not found");
        }
    }

    @Override
    public void deleteAirline(String airlineId) {
        Airline fetchedAirline = airlineDao.getAirline(airlineId);
        if(Objects.isNull(fetchedAirline)){
            throw new NoSuchElementException("No Airline found");
        }
        airlineDao.deleteAirline(fetchedAirline);
    }

    @Override
    public List<Airline> getAllAirline() {
        return airlineDao.viewAirlines();
    }

    @Override
    public Airline getAirline(String airlineId) {
        Airline airline = airlineDao.getAirline(airlineId);
        if(Objects.isNull(airline)){
            throw new NoSuchElementException("Airline not found");
        }
        return airline;
    }
}
