package com.ats.service;

import com.ats.dao.BookingDao;
import com.ats.dao.FlightDao;
import com.ats.dao.UserDao;
import com.ats.model.user.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    private FlightDao flightDao;
    private BookingDao bookingDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, FlightDao flightDao, BookingDao bookingDao) {
        this.flightDao = flightDao;
        this.bookingDao = bookingDao;
        this.userDao = userDao;
    }


    @Override
    public Passenger registerPassenger(Passenger passenger) {
        return userDao.savePassenger(passenger);
    }

    @Override
    public Passenger updatePassengerDetails(int passengerId, Passenger passenger) {
        Passenger dbPassenger = userDao.getPassenger(passengerId);
        if(Objects.nonNull(dbPassenger)){
            dbPassenger.setUserFullName(passenger.getUserFullName());
            dbPassenger.setUserPassword(passenger.getUserPassword());
            dbPassenger.setUserContact(passenger.getUserContact());
            dbPassenger.setUserEmail(passenger.getUserEmail());
            dbPassenger.setPassengerPassport(passenger.getPassengerPassport());
            return userDao.savePassenger(dbPassenger);
        }
        else {
            throw new NoSuchElementException("There is no such passenger");
        }
    }

    @Override
    public Passenger getPassenger(int passengerId) {
        Passenger passenger = userDao.getPassenger(passengerId);;
        if(Objects.isNull(passenger)){
            throw new NoSuchElementException("There is no such passenger");
        }
        return passenger;
    }

}
