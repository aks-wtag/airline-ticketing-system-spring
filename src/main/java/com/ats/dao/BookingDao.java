package com.ats.dao;

import java.util.List;
import com.ats.model.booking.Booking;
import com.ats.model.user.Passenger;

public interface BookingDao {
    Booking getBooking(int bookingId);
    void deleteBooking(Booking booking);

    List<Booking> getBookings(Passenger passenger);

    List<Booking> getBookings();

    Booking bookTicket(Booking booking);
}
