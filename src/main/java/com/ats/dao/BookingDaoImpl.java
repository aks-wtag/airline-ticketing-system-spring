package com.ats.dao;

import com.ats.model.booking.Booking;
import com.ats.model.user.Passenger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class BookingDaoImpl implements BookingDao{
    private SessionFactory sessionFactory;
    private Logger logger;

    @Autowired
    public BookingDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public Booking getBooking(int bookingId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Booking.class, bookingId);
        } catch (Exception e) {
            logger.info("Getting booking info failed");
            logger.info(e.toString());
            return null;
        }
    }

    @Override
    public void deleteBooking(Booking booking) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(booking);
        } catch (Exception e) {
            logger.info("Deleting booking failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getBookings(Passenger passenger) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "From Booking b WHERE b.passenger = :passenger";
            Query query = session.createQuery(hql);
            query.setParameter("passenger", passenger);
            return query.list();
        } catch (Exception e) {
            logger.info("Getting booking info failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getBookings() {
        try {
            Session session = sessionFactory.getCurrentSession();

            Criteria cr = session.createCriteria(Booking.class);

            return new ArrayList<Booking>(cr.list());
        } catch (Exception e) {
            logger.info("Getting booking info failed");
            logger.info(e.toString());
            return null;
        }
    }

    //    -------Booking-----------
    @Override
    public Booking bookTicket(Booking booking) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(booking);
            return booking;
        } catch (HibernateException e) {
            logger.info("Booking failed");
            throw new RuntimeException(e);
        }
    }
}
