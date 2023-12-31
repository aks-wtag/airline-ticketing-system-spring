package com.ats.service;

import com.ats.model.user.Passenger;

public interface UserService {
    Passenger registerPassenger(Passenger passenger);

    Passenger updatePassengerDetails(int passengerId, Passenger passenger);

    Passenger getPassenger(int passengerId);
}
