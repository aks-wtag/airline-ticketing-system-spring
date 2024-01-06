package com.ats.dao;

import com.ats.model.airline.Airline;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class AirlineDaoImpl implements AirlineDao{
    private SessionFactory sessionFactory;

    private Logger logger;

    @Autowired
    public AirlineDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public Airline saveAirline(Airline airline) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(airline);
            logger.info("Saved airline details");
            return airline;
        } catch (HibernateException e) {
            logger.info("Saving airline details failed..");
            throw new HibernateException(e);
        }
    }

//    @Override
//    public boolean modifyAirline(Airline airline) {
//        try {
//            Session session = sessionFactory.getCurrentSession();
//            session.update(airline);
//            logger.info("Updating airline successfully");
//            return true;
//        } catch (Exception e) {
//            logger.info("Updating airline failed");
//            logger.info(e.toString());
//            return false;
//        }
//    }

    @Override
    public List<Airline> viewAirlines() {
        try {
            Session session = sessionFactory.getCurrentSession();
            Criteria cr = session.createCriteria(Airline.class);
            return new ArrayList<Airline>(cr.list());
        } catch (HibernateException e) {
            logger.info("Getting Airlines failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAirline(Airline airline) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(airline);
            logger.info("Deleted airline successfully");
        }
        catch (HibernateException e) {
            logger.info("Deleting airline failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Airline getAirline(String airlineId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Airline.class, airlineId);
        } catch (HibernateException e) {
            logger.info("Failed to get airline info");
            throw new RuntimeException(e);
        }
    }
}
