package com.ats.dao;



import com.ats.model.user.Passenger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao{
    private SessionFactory sessionFactory;
    private Logger logger;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.logger = Logger.getLogger(this.getClass().getName());
    }
    @Override
    public Passenger savePassenger(Passenger passenger) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(passenger);
            return passenger;
        } catch (HibernateException e) {
            logger.info("Passenger registration failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Passenger getPassenger(int passengerId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.get(Passenger.class, passengerId);
        } catch (HibernateException e) {
            logger.info("Passenger search failed");
            throw new RuntimeException(e);
        }
    }
}
