package com.ats.service;

import com.ats.dao.BookingDao;
import com.ats.model.booking.Booking;
import com.ats.model.flight.Flight;
import com.ats.model.user.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{
    BookingDao bookingDao;
    UserService userService;
    FlightService flightService;

    @Autowired
    public BookingServiceImpl(BookingDao bookingDao, UserService userService, FlightService flightService) {
        this.bookingDao = bookingDao;
        this.flightService = flightService;
        this.userService = userService;
    }


    @Override
    public Booking bookTicket(Booking booking) {
        Flight modifiedFLight = booking.getFlight();
        modifiedFLight.setRemainingSeats(modifiedFLight.getRemainingSeats()-1);
        flightService.updateFlight(modifiedFLight.getFlightId(), modifiedFLight);

        return bookingDao.bookTicket(booking);
    }

    @Override
    public List<Booking> getBookings(int passengerId) {
        Passenger passenger = userService.getPassenger(passengerId);
        return bookingDao.getBookings(passenger);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getBookings();
    }

    @Override
    public void deleteTicket(int passengerId, int bookingId) {
        Booking fetchedBooking = bookingDao.getBooking(bookingId);
        if(Objects.isNull(fetchedBooking)){
            throw new NoSuchElementException("No such booking found");
        }

        Flight modifiedFLight = fetchedBooking.getFlight();
        modifiedFLight.setRemainingSeats(modifiedFLight.getRemainingSeats()+1);
        flightService.updateFlight(modifiedFLight.getFlightId(), modifiedFLight);

        bookingDao.deleteBooking(fetchedBooking);
    }
}
