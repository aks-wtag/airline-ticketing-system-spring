package com.ats.dao;


import com.ats.model.user.Passenger;

public interface UserDao {
    Passenger savePassenger(Passenger passenger);
//    boolean modifyPassengerDetails(Passenger passenger);
    Passenger getPassenger(int passengerId);

//    public Boolean bookTicket(Booking booking);

//    public boolean deletePerson(String emailAddress);
}