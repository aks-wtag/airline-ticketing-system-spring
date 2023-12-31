package com.ats.dao;

import com.ats.model.flight.Flight;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class FlightDaoImpl implements FlightDao{
    private final SessionFactory sessionFactory;
    private final Logger logger;

    @Autowired
    public FlightDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public Flight saveFlight(Flight flight) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(flight);
            return flight;
        } catch (HibernateException e) {
            logger.info("Adding flight failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flight> getFlights() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria cr = session.createCriteria(Flight.class);
            return new ArrayList<Flight>(cr.list());
        } catch (HibernateException e) {
            logger.info("Getting flights failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Flight getFlight(int flightId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Flight.class, flightId);
        } catch (HibernateException e) {
            logger.info("Getting flight failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Flight> getFilteredFlights(String departureLocation, String arrivalLocation, String departureDate) {
        try {
            Session session = sessionFactory.getCurrentSession();

            String hql = "from Flight as f where f.departureLocation=:departureLocation and f.arrivalLocation=:arrivalLocation and f.departureDate=:departureDate";
            Query query = session.createQuery(hql);
            query.setParameter("departureLocation", departureLocation);
            query.setParameter("arrivalLocation", arrivalLocation);
            query.setParameter("departureDate", departureDate);

            return (List<Flight>) query.list();
        } catch (HibernateException e) {
            logger.info("Getting filtered flights failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFlight(Flight flight) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(flight);
        }
        catch (HibernateException e) {
            logger.info("Deleting flights failed");
            throw new RuntimeException(e);
        }
    }
}
