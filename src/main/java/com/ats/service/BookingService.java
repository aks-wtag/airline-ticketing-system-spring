package com.ats.service;

import com.ats.model.booking.Booking;

import java.util.List;

public interface BookingService {

    Booking bookTicket(Booking booking);

    List<Booking> getBookings(int passengerId);
    List<Booking> getAllBookings();

    void deleteTicket(int passengerId, int bookingId);
}
