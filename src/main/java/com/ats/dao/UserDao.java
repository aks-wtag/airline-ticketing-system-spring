package com.ats.dao;


import com.ats.model.user.Passenger;

public interface UserDao {
    Passenger savePassenger(Passenger passenger);
    Passenger getPassenger(int passengerId);

}